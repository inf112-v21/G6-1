package inf112.skeleton.app.networking;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.networking.listeners.ServerListener;
import inf112.skeleton.app.networking.packets.Packets;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;


// Creates a Kryonet server.
// The server can send and receive data from and to clients.
// run() starts the server

public class GameServer implements Runnable {
    private String map = "RiskyExchange.tmx";

    // Server object
    Server server;
    ServerListener serverListener;
    public InetAddress address;


    //What ports to be used
    static int udpPort = 54777, tcpPort = 54555;


    public GameServer(String map) {
        this.map = map;
    }


    /**
     * Starts the server, tries to bind it and fetches host-ip for futher use.
     *
     */
    public void run() {
        server = new Server();
        serverListener = new ServerListener(server,map);

        System.out.println("Creating the server...");

        server.addListener(serverListener);

        try {
            server.bind(tcpPort, udpPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Network.register(server);
        server.start();

        try {
            address = InetAddress.getLocalHost();
            System.out.println(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        System.out.println("Server has started:");
    }

    public void stop() {
        server.stop();
    }

    public InetAddress getAddress() {
        return address;
    }

    static class playerConnected extends Connection {
        public String name;
    }

    /**
     * Methods for keeping the names updated within the server, can be used to add and remove names
     * //TODO Gjør den faktisk det? Hvis ikke bør den det
     *
     */
    public void updateNames() {
        // Collects names for every connected
        Connection[] connections = server.getConnections();

        ArrayList connectedNames = new ArrayList(connections.length);
        //Checks the players connected and adds them to connectedNames.
        for(int i = connections.length - 1; i>=0; i--) {
            playerConnected connection = (playerConnected)connections[i];
            connectedNames.add(connection.name);
        }

        //names in updateNames takes all the connectedNames, and server sends it to everyone on TCP
        Packets.UpdateNames updateNames = new Packets.UpdateNames();
        updateNames.names = (String[])connectedNames.toArray(new String[connectedNames.size()]);
        server.sendToAllTCP(updateNames.names);
    }


    // used in test
    public ArrayList<Card> getCardsLastUsed() {
        if (serverListener.getReceivedCards().isEmpty()) {
            return null;
        } else {
            return serverListener.getReceivedCards().get(serverListener.getReceivedCards().size() - 1).playedCards;
        }
    }

    public void delete() {
        try {
            server.close();
        } catch (Exception e) {

        }
    }

}



