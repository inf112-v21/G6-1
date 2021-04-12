package inf112.skeleton.app.networking.listeners;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.networking.packets.Packets;


/**
 * The ClientListener class receives and sends data to and from the server.
 * Calls methods in the game to be able to send data to game.
 */
public class ClientListener extends Listener {
    private Game game;
    private Client client;
    public Packets.CardsPacket cards;
    public Packets.NamePacket name;


    /**
     * Initializes the listener class.
     * @param client Kryonet client that is connected to the server.
     * @param game game class that is played
     */
    public void initialize(Client client, Game game) {
        this.client = client;
        this.game = game;

        cards = new Packets.CardsPacket();
    }

    /**
     * Called by the client, if connected,
     * send a message to the server and set the connection to true.
     * @param connection connected
     */
    public void connected(Connection connection  ) {
        System.out.println("Cl: Established connection");
    }


    public void disconnected(Connection connection ) {
        System.out.println("Cl: You have been disconnected from the server");
    }



    public void received(Connection c, Object object) {
        if (object instanceof Packets.PlayerNumberPacket)
        {
            Packets.PlayerNumberPacket p = (Packets.PlayerNumberPacket) object;
            game.setNumberOfPlayers(p.numberOfPlayers);
        }
        else if (object instanceof Packets.StartGamePackage)
        {
            System.out.println("Starting game");
            game.dealPlayerDecks();
        }
        else if (object instanceof Packets.RoundPacket)
        {
            Packets.RoundPacket roundPacket = (Packets.RoundPacket) object;
            game.executeMoves(roundPacket.playerMoves);

        }
    }


}
