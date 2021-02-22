package inf112.skeleton.app.networking;


import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.Listener;


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
        server.getKryo().register(PacketMessage);
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


}
