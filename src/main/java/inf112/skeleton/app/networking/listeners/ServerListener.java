package inf112.skeleton.app.networking.listeners;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.networking.packets.Packets;

import java.util.ArrayList;

// Listener class for receiving and sending data from clients to all clients.
public class ServerListener extends Listener {
    private final String map;
    private Server server;
    private int playerNumber = 1;

    private ArrayList<Packets.CardsPacket> cardsReceived;

    private String[] playerNames;
    private boolean[] allPlayersReady;
    public boolean[] ShutdownPlayer;

    /**
     *
     * @param server Kryonet server that is being used
     */
    public ServerListener(Server server, String map) {
        this.server = server;
        this.map = map;
        playerNames = new String[6];
        cardsReceived = new ArrayList<>();
        allPlayersReady = new boolean[6];
        ShutdownPlayer = new boolean[6];


    }



    // If client connects to the server, this method is called.
    // It will print out a message and update what players are on the server
    // and update and send the number of players to all clients.

    public void playerIsConnected(Connection connection) {
        System.out.println("Player number " + playerNumber + " has connected to the server");
        playerNumber++;
        Packets.PlayerNumberPacket numberOfPlayers = new Packets.PlayerNumberPacket();
        numberOfPlayers.playerNumber = playerNumber;
        server.sendToAllTCP(numberOfPlayers);
        server.sendToTCP(connection.getID(), map);
    }


    /**
     * If a player disconnects from the server this method will get
     * called and it will print which player disconnected.
     * @param connection
     */
    public void playerIsDisconnected(Connection connection) {
        System.out.println("Player: " + playerNumber + " has been disconnected");
        playerNumber--;
        playerNames[connection.getID()] = null;
        Packets.PlayerNumberPacket numberOfPlayers = new Packets.PlayerNumberPacket();
        numberOfPlayers.playerNumber = playerNumber;
        server.sendToAllTCP(numberOfPlayers);
        Packets.NamePacket name = new Packets.NamePacket();
        server.sendToAllTCP(name);
    }

    /** When something is sent to the server this method gets called and sorts
     *  out what type of message it is before it sends it all clients
     *
     * @param connection
     * @param object
     */
    public void received(Connection connection, Object object) {
        if (object instanceof Packets.MessagePacket) {
            Packets.MessagePacket packet = (Packets.MessagePacket) object;
            server.sendToAllTCP(packet);
        } else if (object instanceof Packets.CardsPacket) {
            Packets.CardsPacket cards = (Packets.CardsPacket) object;
            cardsReceived.add(cards);
            if (cardsReceived.size() == playerNumber) {
                for (Packets.CardsPacket p : cardsReceived) {
                    server.sendToAllTCP(p);
                }
                cardsReceived.clear();
            }
        } else if (object instanceof Packets.StartSignalPacket) {
            Packets.StartSignalPacket startSignalPacket = (Packets.StartSignalPacket) object;
            server.sendToAllTCP(startSignalPacket);
        } else if (object instanceof Packets.NamePacket) {
            Packets.NamePacket name = (Packets.NamePacket) object;
            playerNames[connection.getID()] = name.name[0];
            name.name = playerNames;
            server.sendToAllTCP(name);
        } else if (object instanceof Packets.ReadySignalPacket) {
            Packets.ReadySignalPacket ready = (Packets.ReadySignalPacket) object;
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
            playerNumber--;
            Packets.PlayerNumberPacket numberOfPlayersConnected = new Packets.PlayerNumberPacket();
            numberOfPlayersConnected.playerNumber = playerNumber;
            server.sendToAllTCP(numberOfPlayersConnected);
        }
    }




}
