package inf112.skeleton.app.networking;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.networking.listeners.ClientListener;
import inf112.skeleton.app.networking.packets.Packets;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

public class GameClient extends Listener {
    static com.esotericsoftware.kryonet.Client client;
    static int udpPort = 54777, tcpPort = 54555;

    public Client cl;
    private ClientListener cListener;

    /**
    public GameClient(Game game) {
        client = new Client();
        cListener = new ClientListener();
        udpPort = 54777;
        tcpPort = 54555;

        cListener.initialize(client, game);
        Network.register(client);
        client.addListener(cListener);
    }
    */

    public GameClient(InetAddress ipAddress, Game game){
        client = new Client();
        cListener = new ClientListener();
        udpPort = 54777;
        tcpPort = 54555;
        //this.udpPort = udp;
        //this.tcpPort = tcp;

        cListener.initialize(client, game);
        Network.register(client);
        client.addListener(cListener);

        new Thread(client).start();
        client.start();

        try {
            client.connect(5000, ipAddress, tcpPort, udpPort);
            System.out.println("Work till here");
        }catch (IOException e){
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

    public boolean getC() {
        return cListener.getC();
    }

    // Server alerts all clients to start the game
    public void sendStartSignal() {
        cListener.sendStartSignal();
    }


    public void delete() {
        try {
            client.close();
        } catch (Exception m) {

        }
    }

    public void sendReady(Packets.StartGamePackage signal) {
        cListener.sendReady(signal);
    }

    public void sendPlayerShutDown() {
        cListener.sendRobotShutdownSign();
    }

    public void sendCardsToServer(ArrayList<Card> cards) {
        cListener.sendCardsToServer(cards);
    }

    public ArrayList<Card> getLastCardsSent() {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < cListener.getReceivedCards().playedCards.size(); i++) {
            cards.add(cListener.getReceivedCards().playedCards.get(i));
        }
        return cards;
    }


    public void sendName(String text) {
        Packets.NamePacket name = new Packets.NamePacket();
        name.name = new String[]{ text };
        name.playerId = client.getID();
        cListener.sendName(name);
    }
}
