package inf112.skeleton.app.networking.listeners;

import java.util.ArrayList;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.networking.packets.Packets;


/**
 * The ClientListener class receives and sends data to and from the server.
 * Calls methods in the game to be able to send data to game.
 */
public class ClientListener extends Listener  {

    private Game game;
    private Client client;
    private boolean playerCreated = false;
    private boolean clientHasSentCards = false;
    public int myId;
    public int totalPlayersConnectedToServer = 0;

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
    }

    public void disconnected(Connection connection ) {
        System.out.println("Cl: You have been disconnected from the server");
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

        if (game.myHumanPlayer != null && game.myHumanPlayer.ready && !clientHasSentCards) {
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
         }

        if (object instanceof Packets.PlayerNumberPacket) {
            Packets.PlayerNumberPacket p = (Packets.PlayerNumberPacket) object;
            totalPlayersConnectedToServer = p.numberOfPlayersConnected;
            game.connectedPlayers(totalPlayersConnectedToServer);

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
    }
}
