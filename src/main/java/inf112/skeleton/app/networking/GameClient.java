package inf112.skeleton.app.networking;



import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.networking.listeners.ClientListener;
import inf112.skeleton.app.networking.packets.Packets;

import java.io.IOException;
import java.net.InetAddress;


public class GameClient extends Listener {

    public Client client;
    public ClientListener cListener;
    public boolean receivedPacket = false;
    int udpPort;
    int tcpPort;

    public GameClient(InetAddress ipAddress, Game game){
        client = new Client();
        ClientListener cListener = new ClientListener();
        udpPort = 54777;
        tcpPort = 54555;

        cListener.initialize(client, game);
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

    public GameClient(String ip, boolean c) {
    }

    public boolean getConnection() {
        return cListener.getC();
    }


    public void GameClient(InetAddress ipAddress, Game game, int udp, int tcp) {
        client = new Client();
        cListener = new ClientListener();
        this.udpPort = udp;
        this.tcpPort = tcp;

        cListener.initialize(client, game);
        Network.register(client);
        client.addListener(cListener);


        new Thread(client).start();

        try {
            client.connect(5000, ipAddress, tcp, udp);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    // For testing in ServerClientTest
    public Client getClient() {
        return client;
    }



}
