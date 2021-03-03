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
     * @param game that is played
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
    public void isConnected(Connection connection) {
        System.out.println("Cl: Established connection");
        c = false;
    }


    /** VENTER TIL VILDE HAR LAGET FUNKSJONEN TIL DENNE
     * Sends an array to the server which contains the cards that the
     * player has chosen to play.
     * @param cardsToBePlayed the cards that the player wants to play
     */
    //TODO CardsToBePlayed eller lingende bør bli laget
    /*
    public void sendCardsToServer(CardsToBePlayed[] cardsToBePlayed) {
        Packets.CardsPacket newCards = new Packets.CardsPacket();
        if (cardsToBePlayed != null) {

        }
    }
*/
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


    public void received() {

    }


    // send a text message to other players?



}
