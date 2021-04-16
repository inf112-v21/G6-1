package inf112.skeleton.app.networking;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.player.Player;

import java.io.IOException;


public class RoboClient extends Listener {
    private static Client client;
    private final Game game;
    private String ip;
    private int myId;
    private Player myPlayer;


    public RoboClient(Game game, String ip) throws IOException{
        this.ip = ip;
        this.game = game;

        client = new Client();
        Network.register(client);
        new Thread(client).start();




        client.addListener(new Listener.ThreadedListener(new Listener()) {
            public void connected (Connection connection) {
            }
            public void received (Connection connection, Object object) {
                if (object instanceof Network.playerList) {
                    Network.playerList pl = (Network.playerList)object;
                    pl.list.stream().sorted();
                    myPlayer = pl.list.get(myId);
                }
            }
            public void disconnected (Connection connection) {
                System.exit(0);
            }

        });
        try {
            client.connect(5000, ip, Network.TCPport, Network.UDPport);
            Network.addNewPlayer newPlayer = new Network.addNewPlayer();
            myId = newPlayer.number;
            client.sendTCP(newPlayer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
}