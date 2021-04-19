package inf112.skeleton.app.networking.listeners;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.networking.packets.Packets;

import java.util.ArrayList;
import java.util.HashMap;


public class ServerListener extends Listener {
    private final Server server;
    private final boolean[] allPlayersReady;
    private final HashMap<Integer, ArrayList<Card>> cardsReceived;
    private int numberOfPlayers = 0;

    private final String[] playerNames;
    public final boolean[] ShutdownPlayer;
    public final int MAX_PLAYERS = 3;

    /**
     *
     * @param server Kryonet server that is being used
     */
    public ServerListener(Server server) {
        this.server = server;
        playerNames = new String[MAX_PLAYERS];
        cardsReceived = new HashMap<>();
        allPlayersReady = new boolean[MAX_PLAYERS];
        ShutdownPlayer = new boolean[MAX_PLAYERS];


        for (int i = 0; i < MAX_PLAYERS; i++) {
            ShutdownPlayer[i] = false;
        }
    }


    public void connected(Connection connection) {
        System.out.println("Player " + (numberOfPlayers + 1) + " has connected to the server");
        numberOfPlayers++;

        Packets.PlayerNumberPacket playerPacket = new Packets.PlayerNumberPacket();
        playerPacket.numberOfPlayers = numberOfPlayers;
        server.sendToAllTCP(playerPacket);

        Packets.PlayerIdPacket playerIdPacket = new Packets.PlayerIdPacket();
        playerIdPacket.playerNumber = numberOfPlayers;
        server.sendToAllTCP(playerIdPacket);

        Packets.SendMapNameToPlayer sendMapNameToPlayer = new Packets.SendMapNameToPlayer();
        server.sendToAllTCP(sendMapNameToPlayer);

        if (numberOfPlayers >= 3) {
            this.startGameSession();
        }
    }


    /**
     * If a player disconnects from the server this method will get
     * called and it will print which player disconnected.
     * @param connection connection
     */
    public void disconnected(Connection connection) {
        System.out.println("Player: (" + numberOfPlayers + ") has been disconnected");
        numberOfPlayers--;
        playerNames[connection.getID()] = null;
        Packets.PlayerNumberPacket numberOfPlayers = new Packets.PlayerNumberPacket();
        numberOfPlayers.numberOfPlayers = this.numberOfPlayers;
        server.sendToAllTCP(numberOfPlayers);
    }

    public void sendAllMovesToClients() {
        if (cardsReceived.size() != numberOfPlayers) {
            return;
        }
        Packets.RoundPacket roundPacket = new Packets.RoundPacket();
        roundPacket.playerMoves = cardsReceived;

        server.sendToAllTCP(roundPacket);
        System.out.println("card sent");
        cardsReceived.clear();
    }

    public void startGameSession() {
        System.out.println("Instructing all clients to start the game");
        Packets.StartGamePackage p = new Packets.StartGamePackage();
        server.sendToAllTCP(p);
    }

    /** When something is sent to the server this method gets called and sorts
     *  out what type of message it is before it sends it all clients
     *
     * @param connection connection
     * @param object object
     */
    public void received(Connection connection, Object object) {

        if (object instanceof Packets.CardsPacket) {
            Packets.CardsPacket cards = (Packets.CardsPacket) object;
            cardsReceived.put(cards.playerId, cards.playedCards);
            sendAllMovesToClients();

        } else if (object instanceof Packets.StartSignalPacket) {
            Packets.StartSignalPacket startSignalPacket = (Packets.StartSignalPacket) object;
            server.sendToAllTCP(startSignalPacket);

        } else if (object instanceof Packets.StartGamePackage) {
            Packets.StartGamePackage ready = (Packets.StartGamePackage) object;
            allPlayersReady[connection.getID()] = ready.signal;
            ready.allReady = allPlayersReady;
            server.sendToAllTCP(ready);

        } else if (object instanceof Packets.ShutDownRobotPacket) {
            Packets.ShutDownRobotPacket robotShutdown = (Packets.ShutDownRobotPacket) object;
            ShutdownPlayer[connection.getID()] = true;
            robotShutdown.playersShutdown = ShutdownPlayer;
            server.sendToAllTCP(robotShutdown);
            ShutdownPlayer[connection.getID()] = false;

        } else if (object instanceof Packets.RemovePlayerPacket) {
            numberOfPlayers--;
            Packets.PlayerNumberPacket numberOfPlayersConnected = new Packets.PlayerNumberPacket();
            numberOfPlayersConnected.numberOfPlayers = numberOfPlayers;
            server.sendToAllTCP(numberOfPlayersConnected);
        }
    }
}
