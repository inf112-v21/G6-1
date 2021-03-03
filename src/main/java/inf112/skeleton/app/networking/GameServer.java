package inf112.skeleton.app.networking;

import com.badlogic.gdx.maps.Map;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.networking.Network.PacketMessage;
import inf112.skeleton.app.networking.listeners.ServerListener;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;


// Creates a Kryonet server.
// The server can send and receive data from and to clients.
// run() starts the server

public class GameServer extends Listener {
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
        Network.register(server);

        try {
            server.bind(tcpPort, udpPort);
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.start();

        try {
            address = InetAddress.getLocalHost();
            System.out.println(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        System.out.println("Server has started:O");


    }


    //TODO We need this class for anything to be recieved by server from clients
    public void received(Connection c, Object o){

    }

    /**
     * Method to be used to tell server a new connection has been made, sending a simple respons to server and
     * updating names in the names-variable.
     *
     * @param c - Connection object used to identify user
     */
    public void isConnected(Connection c){
        System.out.println("Received connection from "+ c.getRemoteAddressTCP().getHostString());
        PacketMessage packetMessage = new PacketMessage();
        packetMessage.message = "Heisann!";
        c.sendTCP(packetMessage);
        updateNames();
    }

    /**
     * Method to be used to tell server a user has disconnected, sending a simple respons to server, and updates the new
     * names-list, removing the given user;)
     *
     * @param c - Connection object used to identify user
     */
    public void isDisconnected(Connection c){
        playerConnected Connection = (playerConnected) c;
        if(Connection.name != null) {
            PacketMessage packetMessage = new PacketMessage();
            packetMessage.message = Connection.name + " has disconnected...";
            server.sendToAllTCP(packetMessage);
            updateNames();
        }
        System.out.println("Player disconnected");
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
        Network.updateNames updateNames = new Network.updateNames();
        updateNames.names = (String[])connectedNames.toArray(new String[connectedNames.size()]);
        server.sendToAllTCP(updateNames.names);
    }

}



