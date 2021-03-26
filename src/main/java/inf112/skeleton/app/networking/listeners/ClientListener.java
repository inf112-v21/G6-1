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
        // if player sends no cards
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
        // Player connection handling
        if (object instanceof Packets.PlayerNumberPacket)
        {
            Packets.PlayerNumberPacket p = (Packets.PlayerNumberPacket) object;
            game.setNumberOfPlayers(p.numberOfPlayers);
        }
        else if (object instanceof Packets.PlayerIdPacket)
        {
            Packets.PlayerIdPacket p = (Packets.PlayerIdPacket) object;
            int yourPlayerNumber = p.playerNumber;
            // TODO ask Erlend set your player number to yourPlayerNumber
        }

        // Game start / end
        else if (object instanceof Packets.StartGamePackage)
        {
            System.out.println("Starting game");
            // TODO we only need to deal the players player deck - not all players
            game.dealPlayerDecks();
        }

        // Round handling
        else if (object instanceof Packets.RoundPacket)
        {
            Packets.RoundPacket roundPacket = (Packets.RoundPacket) object;
            game.executeMoves(roundPacket.playerMoves);

            // TODO after all moves are done we either start a new round or end the game.
            //  executeMoves currently deals cards to everyone, but that doesn't work for MP
            // We probably want the server to send each player a packet of cards they may choose
            //  other possibility which is quicker: deal cards to your own player in this else if

        }

        // Ready signal handling
        else if (object instanceof Packets.StartSignalPacket){
            // TODO implement once basic network works
            // public boolean start;
        }
        else if (object instanceof Packets.StartGamePackage) {
            // TODO implement once basic network works
            Packets.StartGamePackage ready = (Packets.StartGamePackage) object;
            game.getAllReady(ready.allReady);
        }
        else if (object instanceof Packets.ShutDownRobotPacket) {
            // TODO what is the intended usage of this packet?
            Packets.ShutDownRobotPacket shutDownRobot = (Packets.ShutDownRobotPacket) object;
            game.shutDownPlayer();
        }
    }

    public Packets.CardsPacket getReceivedCards() {
        return cards;
    }

    // Returns true if connected to the server
    public boolean getC() {
        return c;
    }


    public void sendReady(Packets.StartGamePackage signal) {
        client.sendTCP(signal);
    }

    // Notifies the player to shut down their board piece
    public void sendRobotShutdownSign() {
        Packets.ShutDownRobotPacket shutDownRobotPacket = new Packets.ShutDownRobotPacket();
        client.sendTCP(shutDownRobotPacket);
    }

    // Deleted the player so that it cannot play cards.
    public void removeAPlayerFromTheServer() {
        Packets.RemovePlayerPacket removePlayerPacket = new Packets.RemovePlayerPacket();
        client.sendTCP(removePlayerPacket);
    }



}
