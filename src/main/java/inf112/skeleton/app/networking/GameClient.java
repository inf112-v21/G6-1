package inf112.skeleton.app.networking;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;
import com.jcraft.jogg.Packet;
import inf112.skeleton.app.networking.Network;
import inf112.skeleton.app.networking.Network.PacketMessage;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.networking.listeners.ClientListener;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;

public class GameClient extends Listener {
    static com.esotericsoftware.kryonet.Client client;
    static int udpPort = 54777, tcpPort = 54555;

    private ClientListener cListener;


    /**
     * Used to create a client-object. Registers same packets as server does
     *
     * @param ip - InetAddress object
     * @param game
     */
    public GameClient(InetAddress ip, Game game) {
        client = new Client();
        cListener = new ClientListener();
        cListener.initialize(client, game);

        Network.register(client);

        new Thread(client).start();
        client.start();

        try {
            client.connect(5000, ip, tcpPort, udpPort);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Server is not started. Can not connect");
        }
        client.addListener(cListener);
        System.out.println("IP Address: "+ ip.getHostAddress());

    }



//TODO Trenger muligens en recieved her også


}
