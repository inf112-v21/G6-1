package inf112.skeleton.app.networking.listeners;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.networking.packets.Packets;

import java.util.ArrayList;
import java.util.HashMap;

// Listener class for receiving and sending data from clients to all clients.
public class ServerListener extends Listener {
    private final String map;
    private final Server server;
    private final boolean[] allPlayersReady;
    private final HashMap<Integer, ArrayList<Card>> cardsReceived;
    ArrayList<Packets.CardsPacket> testCards;
    private int numberOfPlayers = 0;

    private final String[] playerNames;
    public final boolean[] ShutdownPlayer;
    public final int MAX_PLAYERS = 5;

    /**
     *
     * @param server Kryonet server that is being used
     */
    public ServerListener(Server server, String map) {
        this.server = server;
        this.map = map;
        playerNames = new String[MAX_PLAYERS];
        cardsReceived = new HashMap<Integer, ArrayList<Card>>();
        allPlayersReady = new boolean[MAX_PLAYERS];
        ShutdownPlayer = new boolean[MAX_PLAYERS];


        for (int i = 0; i < MAX_PLAYERS; i++) {
            ShutdownPlayer[i] = false;
        }
    }



    // If client connects to the server, this method is called.
    // It will print out a message and update what players are on the server
    // and update and send the number of players to all clients.



    // TODO må være connected() siden metoden er fra Listener.java (se vekk fra denne?)
    public void connected(Connection connection) {
        System.out.println("Player " + (numberOfPlayers + 1) + " has connected to the server");
        numberOfPlayers++;

        // Send updated number of players to all players
        Packets.PlayerNumberPacket playerPacket = new Packets.PlayerNumberPacket();
        playerPacket.numberOfPlayers = numberOfPlayers;
        //game.setNumberOfPlayers(playerPacket.numberOfPlayers);
        server.sendToAllTCP(playerPacket);


        // Tell the new player their player number
        Packets.PlayerIdPacket playerIdPacket = new Packets.PlayerIdPacket();
        playerIdPacket.playerNumber = numberOfPlayers;
        server.sendToAllTCP(playerIdPacket);

        // Send map name to player
        // Made into packet, since it is easier to handle
        Packets.SendMapNameToPlayer sendMapNameToPlayer = new Packets.SendMapNameToPlayer();
        server.sendToAllTCP(sendMapNameToPlayer);


        // TODO we automatically start the game when we have 3 players
        // TODO add a start button to the gui of the host
        //  which should trigger starting the game
        if (numberOfPlayers >= 3) {
            this.startGameSession();
        }
    }


    /**
     * If a player disconnects from the server this method will get
     * called and it will print which player disconnected.
     * @param connection
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
     * @param connection
     * @param object
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

    /**
     * Used to test if the right cards are sent and received
     * @return the cards that were sent.
     */

    public ArrayList<Packets.CardsPacket> getReceivedCards() {
        if (testCards.size() > 4) {
            testCards.clear();
        }
        return testCards;
    }
}
