package inf112.skeleton.app.networking.listeners;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.networking.packets.Packets;
import inf112.skeleton.app.card.*;

import java.nio.file.Path;


/**
 * The ClientListener class receives and sends data to and from the server.
 * Calls methods in the game to be able to send data to game.
 */
public class ClientListener extends Listener {
    private Client cl;
    private Game game;
    private Packets.MessagePacket message;
    private Packets.CardsPacket cards;
    private boolean c = false;


    /**
     *
     * @param cl the client that is connected to the server.
     * @param game is whats played
     */
    public void initialize(Client cl, Game game) {
        this.cl = cl;
        this.game = game;
        message = new Packets.MessagePacket();
        cards = new Packets.CardsPacket();
    }

    /**
     * Called by the client, if connected,
     * send a message to the server and set the connection to true.
     * @param connection
     */
    public void connected(Connection connection) {
        System.out.println("Cl: Established connection");
        this.c = true;
    }


    /** VENTER TIL VILDE HAR LAGET FUNKSJONEN TIL DENNE
     * Sends an array to the server which contains the cards that the
     * player has chosen to play.
     * @param cardsToBePlayed the cards that the player wants to play
     */
    //TODO CardsToBePlayed eller lingende bør bli laget

    public void sendCardsToServer(CardDeck cardsToBePlayed) {
        Packets.CardsPacket cards = new Packets.CardsPacket();
        if (cardsToBePlayed != null) {
            cards.playedCards = new int[cardsToBePlayed.length][4];
            for (int i = 0; i < cardsToBePlayed.length; i++) {

            }
        }
    }



    /**
     * Alerts all the clients by sending the start signal to the server.
     */
    public void sendStartSignalToServer() {
        Packets.StartSignalPacket startSignalPacket = new Packets.StartSignalPacket();
        startSignalPacket.start = true;
        cl.sendTCP(startSignalPacket);
    }


    public void sendNameToServer(Packets.NamePacket name) {
        cl.sendTCP(name);
    }


    public void received(Connection c, Object object) {
        if (object instanceof Packets.CardsPacket) {
            Packets.CardsPacket p = (Packets.CardsPacket) object;
            cards = p;
            game.isReady(p);
        } else if (object instanceof Packets.PlayerNumberPacket) {
            Packets.PlayerNumberPacket p = (Packets.PlayerNumberPacket) object;
            game.setNumberOfPlayers(p.playerNumber);
        } else if (object instanceof Packets.StartSignalPacket){
            // public boolean start;
        } else if (object instanceof Packets.NamePacket) {
            Packets.NamePacket name = (Packets.NamePacket) object;
            // game.receivesNames(names);
        } else if (object instanceof Packets.ReadySignalPacket) {
            Packets.ReadySignalPacket ready = (Packets.ReadySignalPacket) object;
            game.getAllReady(ready.allReady);
        } else if (object instanceof Packets);
    }

    public void disconnected() {
        this.c = false;
        System.out.println("Cl: You have been disconnected");
    }



}
