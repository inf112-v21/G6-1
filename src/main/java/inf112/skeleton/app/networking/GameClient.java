package inf112.skeleton.app.networking;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.networking.listeners.ClientListener;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;


public class GameClient extends Listener {
    static com.esotericsoftware.kryonet.Client client;
    final int udpPort;
    final int tcpPort;

    public GameClient(InetAddress ipAddress, Game game){
        client = new Client();
        ClientListener cListener = new ClientListener();
        udpPort = 54777;
        tcpPort = 54555;

        cListener.initialize(game);
        Network.register(client);
        client.addListener(cListener);

        new Thread(client).start();
        client.start();

        try {
            client.connect(5000, ipAddress, tcpPort, udpPort);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean connect(String ip) {
        new Thread(client).start();
        try {
            client.connect(5000, ip, tcpPort, udpPort);
            System.out.println("IP Address: "+ ip);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Server is not started. Can not connect");
            return false;
        }
    }

    public int getId() {
        return client.getID();
    }
}
