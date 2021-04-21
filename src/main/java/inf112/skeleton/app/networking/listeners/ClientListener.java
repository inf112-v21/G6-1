package inf112.skeleton.app.networking.listeners;

import java.util.ArrayList;
import java.util.HashMap;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.networking.packets.Packets;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Action;


/**
 * The ClientListener class receives and sends data to and from the server.
 * Calls methods in the game to be able to send data to game.
 */
public class ClientListener extends Listener {
    private boolean c = false;
    private Game game;
    private Client client;
    public Packets.CardsPacket cards;
    public Packets.UpdateNames name;
    private boolean playerCreated = false;
    public int myId;
    public Player dummyPlayer;


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
    public void connected(Connection connection) {
        System.out.println("Cl: Established connection");
        c = true;
    }

     /**
     * Sends an array of cards to the server to be played.
     * @param cardLogic The cards the player want to be played.
     */
    public void sendCards(HashMap<Integer, ArrayList<Card>> cardLogic){
        Packets.RoundPacket newCards = new Packets.RoundPacket();
        if(cardLogic != null){
            newCards.playerMoves = new HashMap<Integer, ArrayList<Card>>();
            for (int i = 0; i < cardLogic.size(); i++) {
                newCards.playerMoves = cardLogic;
            }
        }else {
            newCards.playerMoves = null;
        }
        newCards.playerId = client.getID();
        client.sendTCP(newCards);
    }

     /**
     * Sends a start signal to the server alerting all clients to start the game.
     */
    public void sendStartSignal() {
        Packets.StartSignalPacket startSignal = new Packets.StartSignalPacket();
        startSignal.start = true;
        client.sendTCP(startSignal);
    }

        /**
     * Sends a name to the server
     * @param name A Packet with a single name in a String[]
     */
    public void sendName(Packets.UpdateNames name) {
        this.name = this.name;
        client.sendTCP(name);
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

        if (object instanceof Packets.PlayerNumberPacket) {
            Packets.PlayerNumberPacket p = (Packets.PlayerNumberPacket) object;
            dummyPlayer = game.setNumberOfPlayers(p.numberOfPlayersConnected);
            if (playerCreated == false) {
                playerCreated = true;

                Packets.playerInfo playerInfo = new Packets.playerInfo();
                myId = p.numberOfPlayersConnected -1;

                for (int playerID = 0; playerID < game.getNumberOfPlayers(); playerID++) {

                    game.createPlayers(playerID);
                    ArrayList<Float> playerCoordinates = new ArrayList<>();
                    playerCoordinates.add(dummyPlayer.playerCurrentXPosition);
                    playerCoordinates.add(dummyPlayer.playerCurrentYPosition);
                    playerInfo.playerInfo.put(myId, playerCoordinates);
                }
                client.sendTCP(playerInfo);
            }
        }
        else if (object instanceof Packets.playerInfo){
            Packets.playerInfo playerInfo = (Packets.playerInfo) object;
            System.out.println(playerInfo.playerInfo);
            game.updatePlayerInfo(playerInfo.playerInfo);
        }

        else if (object instanceof Packets.StartGamePackage) {
            System.out.println("Starting game");
            game.dealPlayerDecks();
        }
        else if (object instanceof Packets.RoundPacket) {
            Packets.RoundPacket roundPacket = (Packets.RoundPacket) object;
            game.executeMoves(roundPacket.playerMoves);
        }

    }

    public boolean getConnection(){
        return c;
    }

    public void sendReady(Packets.StartGamePackage signal) {
        client.sendTCP(signal);
    }

    /**
     * Sends a message tot he server that this player is powering down their robot
     */
    public void sendShutdownRobot() {
        Packets.ShutDownRobotPacket shutdownRobot = new Packets.ShutDownRobotPacket();
        client.sendTCP(shutdownRobot);
    }

    /**
     * Removes this player from playing anymore cards.
     */
    public void removeOnePlayerFromServer() {
        Packets.RemovePlayerPacket removePlayer = new Packets.RemovePlayerPacket();
        client.sendTCP(removePlayer);
    }

}
