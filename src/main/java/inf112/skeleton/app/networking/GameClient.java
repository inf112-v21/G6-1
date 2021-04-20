package inf112.skeleton.app.networking;

import java.util.ArrayList;
import java.util.HashMap;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.networking.listeners.ClientListener;
import inf112.skeleton.app.networking.packets.Packets;
import inf112.skeleton.app.card.Card;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;


public class GameClient extends Listener {

    public Client client;
    private ClientListener cListener;

    final int udpPort;
    final int tcpPort;


    public GameClient(InetAddress ipAddress, Game game){
        client = new Client();
        ClientListener cListener = new ClientListener();
        udpPort = 54777;
        tcpPort = 54555;

        cListener.initialize(client, game);
        Network.register(client);
        client.addListener(cListener);

        new Thread(client).start();
        client.start();

        try {
            client.connect(5000, ipAddress, tcpPort, udpPort);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


/**
     * Sends an array of ProgramCards to the server.
     *
     */
    public void sendCards(HashMap<Integer, ArrayList<Card>> cardLogic){
        cListener.sendCards(cardLogic);
    }

        /**
     *
     * @return returns the last cards sent to the server.
     */
    // public NonTextureProgramCard[] getLastCardTransfer(){
    //     NonTextureProgramCard[] cards = new NonTextureProgramCard[cListener.getCards().programCards.length];
    //     for (int i = 0; i < cListener.getCards().programCards.length; i++) {
    //         cards[i] = CardTranslator.intToProgramCard(cListener.getCards().programCards[i]);
    //     }
    //     return cards;
    // }

    public int getId() {
        return client.getID();
    }

    public boolean getConnection(){
        return cListener.getConnection();
    }

    public void sendStartSignal() {
        cListener.sendStartSignal();
    }

        /**
     * Sends the username of the player to the server
     * @param text username of the player
     */
    public void sendName(String text) {
        Packets.UpdateNames name = new Packets.UpdateNames();
        name.names = new String[]{text};
        name.playerId = client.getID();
        cListener.sendName(name);
    }


    /**
     * Sends a boolean to the server telling everybody that this player is ready
     * @param signal A packet with booleans
     */
    public void sendReady(Packets.StartGamePackage signal) {
        cListener.sendReady(signal);
    }

    /**
     * Sends a message to the server that this player is powering down their robot.
     */
    public void sendShutdownRobot() {
        cListener.sendShutdownRobot();
    }

    /**
     * Removes this player from playing anymore cards.
     */
    public void removeOnePlayerFromServer() {
        cListener.removeOnePlayerFromServer();
    }

    /**
     * Sends a empty list of cards
     */
    public void sendEmptyCards() {
        cListener.sendCards(null);
    }
}
