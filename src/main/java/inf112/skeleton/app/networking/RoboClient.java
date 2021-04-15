package inf112.skeleton.app.networking;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.game.Game;

import java.io.IOException;
import java.util.ArrayList;


public class RoboClient extends Listener {
    Client client;
    private final Game game;
    private String ip;
    private ArrayList<Card> chosenCards;


    public RoboClient(Game game, String ip) throws IOException{
        this.ip = ip;
        this.game = game;


        client = new Client();
        Network.register(client);
        client.start();

        client.addListener(new Listener.ThreadedListener(new Listener()) {
            public void connected (Connection connection) {
            }
            public void received (Connection connection, Object object) {
            }
            public void disconnected (Connection connection) {
                System.exit(0);
            }
        });
        try {
            client.connect(5000, ip, Network.TCPport, Network.UDPport);
            // Server communication after connection can go here, or in Listener#connected().
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
}