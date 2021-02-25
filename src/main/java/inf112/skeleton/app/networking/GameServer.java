package inf112.skeleton.app.networking;




import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.networking.Network.PacketMessage;


import java.io.IOException;
import java.util.ArrayList;


public class GameServer extends Listener {
    //Object for server
    Server server;

    //What ports to be used
    static int udpPort = 54777, tcpPort = 54555;


    //Server is being created, and bound to chosen ports then started.
    public void run() {
        server = new Server();
        System.out.println("Creating the server...");

        Network.register(server);

        server.getKryo().register(PacketMessage.class);
        server.getKryo().register(int[].class);



        try {
            server.bind(udpPort, tcpPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.start();

        System.out.println("Server has started:O");

        server.addListener(this);
    }

    //Used when someone connects to server
    public void isConnected(Connection connector){
        System.out.println("Received connection from "+ connector.getRemoteAddressTCP().getHostString());
        PacketMessage packetMessage = new PacketMessage();
        packetMessage.message = "Heisann!";

        connector.sendTCP(packetMessage);
    }
    //Used when someone disconnects from server
    public void isDisconnected(Connection connector){
        playerConnected Connection = (playerConnected) connector;
        if(Connection.name != null) {
            PacketMessage packetMessage = new PacketMessage();
            packetMessage.message = Connection.name + " has disconnected...";
            server.sendToAllTCP(packetMessage);
            updateNames();
        }
        System.out.println("Player disconnected");
    }

    static class playerConnected extends Connection {
        public String name;

    }

    void updateNames() {
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
public void launchServer(){
    if(server == null); //If the server is already set-up
        print("Server already exsists")
    this.server = new Server(this); //Not sure if (this) is needed.
    this.client = new Client(this); //Probably more parameteres needed for client, such as viewport or ip.
                                           //Might have to use a if-sentence for this, or try-catch
}
 */
