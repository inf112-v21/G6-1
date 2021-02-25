package inf112.skeleton.app.networking;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;
import com.jcraft.jogg.Packet;
import inf112.skeleton.app.networking.Network.PacketMessage;

import java.io.IOException;

public class Client extends Listener {
    static com.esotericsoftware.kryonet.Client client;
    static String ip = "localhost";
    static int udpPort = 54777, tcpPort = 54555;
    static boolean messageReceived = false;


    public void runClient() throws IOException {
        client = new com.esotericsoftware.kryonet.Client();
        client.getKryo().register(PacketMessage.class);
        client.start();
        client.connect(10000, "192.168.0.4", 54555, 54777);
        client.addListener(this);

        System.out.println("Client should now be waiting for a packet...\n");


    public void received(Connection c, Object p){

        }



    }

}
