package inf112.skeleton.app.networking.listeners;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.networking.packets.Packets;

import java.util.ArrayList;



/**
 * The ClientListener class receives and sends data to and from the server.
 * Calls methods in the game to be able to send data to game.
 */
public class ClientListener extends Listener {
    private Client client;
    private Game game;
    public Packets.CardsPacket cards;
    public Packets.NamePacket name;
    private boolean c = false;


    /**
     *
     * @param client the client that is connected to the server.
     * @param game is whats played
     */
    public void initialize(Client client, Game game) {
        this.client = client;
        this.game = game;

        cards = new Packets.CardsPacket();
    }

    /**
     * Called by the client, if connected,
     * send a message to the server and set the connection to true.
     * @param connection
     */
    public void connected(Connection connection  ) {
        System.out.println("Cl: Established connection");
        this.c = true;
    }


    /** Sends an array to the server which contains the cards that the
     * player has chosen to play.
     * @param cards the cards that the player wants to play
     */

    public void sendCardsToServer(ArrayList<Card> cards) {
        if (cards.size() != 5) {
            return;
        }

        Packets.CardsPacket cardPacket = new Packets.CardsPacket();
        cardPacket.playedCards = cards;
        cardPacket.playerId = client.getID();
        client.sendTCP(cardPacket);
    }



    /**
     * Alerts all the clients by sending the start signal to the server.
     */
    public void sendStartSignal() {
        Packets.StartSignalPacket startSignalPacket = new Packets.StartSignalPacket();
        startSignalPacket.start = true;
        client.sendTCP(startSignalPacket);
    }


    /**
     * Sends a String[] name to the sever
     * @param name
     */
    public void sendName(Packets.NamePacket name) {
        client.sendTCP(name);
    }

    public void disconnected(Connection connection ) {
        this.c = false;
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

    public Packets.CardsPacket getReceivedCards() {
        return cards;
    }

    public boolean getC() {
        return c;
    }


    public void sendReady(Packets.StartGamePackage signal) {
        client.sendTCP(signal);
    }

    public void sendRobotShutdownSign() {
        Packets.ShutDownRobotPacket shutDownRobotPacket = new Packets.ShutDownRobotPacket();
        client.sendTCP(shutDownRobotPacket);
    }



}
