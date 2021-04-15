package inf112.skeleton.app.networking;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.card.CardDeck;
import inf112.skeleton.app.game.Game;

import java.io.IOException;

public class RoboServer extends Listener{
    private static Server server;
    private final Game game;

    private CardDeck card;


    public RoboServer(final Game game) throws IOException {
        this.game = game;
        server = new Server();

        System.out.println("Server is attempting to start...");

        Network.register(server);


        server.addListener(new Listener() {
           public void received (Connection connection, Object object) {

               if (object instanceof SomeRequest) {
                   SomeRequest request = (SomeRequest)object;
                   System.out.println(request.text);

                   SomeResponse response = new SomeResponse();
                   response.text = "Thanks for connecting";
                   connection.sendTCP(response);
               }

               if (object instanceof Hitpoints) {
                   Hitpoints checkHitpoints = (Hitpoints)object;
                   System.out.println(checkHitpoints.hp);
               }
           }
           public void disconnected (Connection connection) {
           }
       }

        );
        server.bind(Network.TCPport, Network.UDPport);
        server.start();

    }
}



