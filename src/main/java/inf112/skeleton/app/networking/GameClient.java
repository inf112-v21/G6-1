package inf112.skeleton.app.networking;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;
import com.jcraft.jogg.Packet;
import inf112.skeleton.app.networking.Network;
import inf112.skeleton.app.networking.Network.PacketMessage;
import inf112.skeleton.app.game.Game;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;

public class GameClient extends Listener {
    static com.esotericsoftware.kryonet.Client client;
    static InetAddress ip;
    static int udpPort = 54777, tcpPort = 54555;
    static boolean messageReceived = false;


    /**
     *
     * @param ip ip address of the server
     * @param game Game object to send data from and to the server
     */
    public void runClient(InetAddress ip, Game game) throws IOException {

        client = new com.esotericsoftware.kryonet.Client();
        client.getKryo().register(PacketMessage.class);
        client.start();

    Network.register(client);
        // Tries to connect to a server times out after 10 seconds.
        try {
            client.connect(10000, ip, 54555, 54777);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Server is not started. Can not connect");
        }
        client.addListener(this);

        System.out.println("IP Address: "+ ip.getHostAddress());
        System.out.println("Client should now be waiting for a packet...\n");
    }


}
