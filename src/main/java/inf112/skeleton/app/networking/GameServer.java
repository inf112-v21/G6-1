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


    //Server is being created, and bound to chosen ports then started.
    public void run() {
        server = new Server();
        serverListener = new ServerListener(server,map);

        System.out.println("Creating the server...");

        Network.register(server);

        server.addListener(serverListener);

        InetAddress address = new InetAddress();
        try {
            server.bind(udpPort, tcpPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.start();
        System.out.println("Server has started:O");

        //String string = (String) server.getAddress();
        System.out.println(address);
    }








    //When something is sent to the server, received() checks what it is and sends it to GameClient
    public void received(Connection c, Object o){

    }

    //Used when someone connects to server
    public void isConnected(Connection c){
        System.out.println("Received connection from "+ c.getRemoteAddressTCP().getHostString());
        PacketMessage packetMessage = new PacketMessage();
        packetMessage.message = "Heisann!";
        c.sendTCP(packetMessage);
    }

    //Used when someone disconnects from server
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

/*

//TODO Launche serveren i Game; forslag til metode til det:
public InetAddress hostNewGame(String map) {
    server = new server(map);
    server.run();

    client = new client(server.getAddress())
    return server.getAddress()
}

public void InetAddress joinNewGame(InetAddress ip) {
    client = new client(ip);

}

 */


