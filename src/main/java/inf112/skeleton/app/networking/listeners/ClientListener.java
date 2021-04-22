package inf112.skeleton.app.networking.listeners;

import java.awt.*;
import java.awt.image.BaseMultiResolutionImage;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.utils.compression.lzma.Base;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import com.jcraft.jogg.Packet;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.networking.Network;
import inf112.skeleton.app.networking.packets.Packets;


/**
 * The ClientListener class receives and sends data to and from the server.
 * Calls methods in the game to be able to send data to game.
 */
public class ClientListener extends Listener  {
    private boolean c = false;
    public boolean gotPackage = false;
    private Game game;
    private Client client;
    private ClientListener cListener;
    private boolean playerCreated = false;
    private boolean clientHasSentCards = false;
    public int myId;
    private int totalPlayersConnectedToServer = 0;





    /**
     * Initializes the listener class.
     * @param client Kryonet client that is connected to the server.
     * @param game game class that is played
     */
    public void initialize(Client client, Game game) {
        this.client = client;
        this.game = game;
    }

    /**
     * Called by the client, if connected,
     * send a message to the server and set the connection to true.
     * @param connection connected
     */
    public void connected(Connection connection) {
        System.out.println("Cl: Established connection");
        c = true;
    }


    public void disconnected(Connection connection ) {
        System.out.println("Cl: You have been disconnected from the server");
        c = false;
    }


    /**
     * Kryonet Client calls this when it receives something from the server,
     * then this method sorts what type of object it is and sends it to the right place in the game class.
     * @param c
     * @param object
     */

    public void received(Connection c, Object object) {

        if (game.graphics.hostGameScreenProcessor.startGame) {
            Packets.StartGamePackage startGamePackage = new Packets.StartGamePackage();
            client.sendTCP(startGamePackage);
            game.graphics.hostGameScreenProcessor.startGame = false;
        }

        if (game.myHumanPlayer != null && game.myHumanPlayer.ready && clientHasSentCards == false) {
            System.out.println("Thomas har fått vite at jeg er klar" + game.myHumanPlayer.id);
            Packets.SendAction HereIsMyCards = new Packets.SendAction();
            HereIsMyCards.actionList = game.getPlayerActionList();
            client.sendTCP(HereIsMyCards);

            game.myHumanPlayer.ready = false;
            clientHasSentCards = true;
        }
         if (object instanceof Packets.SendAction) {
             Packets.SendAction receivedActionsFromAll = (Packets.SendAction) object;
             game.allPlayerMoves=receivedActionsFromAll.actionList;
             clientHasSentCards = false;
             System.out.println(receivedActionsFromAll.actionList);
         }

        if (object instanceof Packets.PlayerNumberPacket) {
            Packets.PlayerNumberPacket p = (Packets.PlayerNumberPacket) object;
            totalPlayersConnectedToServer = p.numberOfPlayersConnected;

            if (!playerCreated) {
                playerCreated = true;

                Packets.playerInfo playerInfo = new Packets.playerInfo();
                myId = p.numberOfPlayersConnected -1;
                game.myId = myId;
                ArrayList<Float> playerCoordinates = new ArrayList<>();
                playerCoordinates.add((float) (myId*300));
                playerCoordinates.add(0F);
                playerInfo.playerInfo.put(myId, playerCoordinates);

                client.sendTCP(playerInfo);
            }
        } else if (object instanceof Packets.playerInfo){
            Packets.playerInfo playerInfo = (Packets.playerInfo) object;
            if (playerInfo.firstPacket) {
                game.createListOfPlayers(playerInfo.playerInfo);
            }
            else {
                game.updatePlayerInfo(playerInfo.playerInfo);
            }
        } else if (object instanceof Packets.StartGamePackage) {
            System.out.println("Starting game");
            game.startGame();
        }
        if (object instanceof Packets.TestPack) {
            System.out.println("Client received test packet");
            Packets.TestPack packet = (Packets.TestPack) object;
            if (packet != null) {
                gotPackage = true;
            }
        }

    }

    public boolean getConnection(){
        return cListener.getConnection();
    }

}
