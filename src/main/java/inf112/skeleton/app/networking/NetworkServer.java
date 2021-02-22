package inf112.skeleton.app.networking;


import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.Listener;
import org.lwjgl.system.CallbackI;


import java.io.IOException;


public class NetworkServer extends Listener{
    //Object for server
    static Server server;

    //What ports to be used
    static int udpPort = 54777, tcpPort = 54555;

    String PacketMessage;

    //Server is being created, and bound to chosen ports then started.
    public void run() {
        server = new Server();


        System.out.println("Creating the server...");
        try {
            server.bind(udpPort, tcpPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.start();
        System.out.println("Server has started:O");

        server.addListener(new NetworkServer());

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
        System.out.println("Player disconnected");
    }


}
