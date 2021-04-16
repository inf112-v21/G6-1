package inf112.skeleton.app.networking;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.card.CardDeck;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.player.Player;

import java.io.IOException;
import java.util.ArrayList;

public class RoboServer extends Listener{
    private static Server server;
    private final Game game;
    private ArrayList<Player> playerList;
    private CardDeck card;
    private int giveClientIdNumber=0;


    public RoboServer(final Game game) throws IOException {
        this.game = game;
        server = new Server();
        playerList = new ArrayList<>();


        System.out.println("Server is attempting to start...");
        Network.register(server);


        server.addListener(new Listener() {
           public void connected (Connection connection) {}
           public void received (Connection connection, Object object) {
                if (object instanceof Network.addNewPlayer) {
                    playerList.add(game.createPlayer(giveClientIdNumber));
                    server.sendToAllTCP(playerList);
                    giveClientIdNumber++;
                }
           }
           public void disconnected (Connection connection) {
           }
       }
        );
        server.bind(Network.TCPport, Network.UDPport);
        new Thread(server).start();
        server.start();
    }
}



