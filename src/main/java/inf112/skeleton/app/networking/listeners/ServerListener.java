package inf112.skeleton.app.networking.listeners;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.networking.packets.Packets;
import inf112.skeleton.app.shared.Action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class ServerListener extends Listener {
    private final Server server;
    private final HashMap<Integer, ArrayList<HashMap<Integer, Action>>> cardsReceived;
    private int numberOfPlayers = 0;
    public final HashMap<Integer, ArrayList<Float>> playerInfoGlobal = new HashMap<>();
    private int checkIfAllClientsAreReady=0;


    /**
     *
     * @param server Kryonet server that is being used
     */
    public ServerListener(Server server) {
        this.server = server;
        cardsReceived = new HashMap<>();
    }



    public void connected(Connection connection) {
        numberOfPlayers++;

        Packets.PlayerNumberPacket playerPacket = new Packets.PlayerNumberPacket();
        playerPacket.numberOfPlayersConnected = numberOfPlayers;
        server.sendToAllTCP(playerPacket);

        Packets.PlayerIdPacket playerIdPacket = new Packets.PlayerIdPacket();
        playerIdPacket.playerNumber = numberOfPlayers;
        server.sendToAllTCP(playerIdPacket);
        }


    /**
     * If a player disconnects from the server this method will get
     * called and it will print which player disconnected.
     * @param connection connection
     */
    public void disconnected(Connection connection) {
        numberOfPlayers--;
        Packets.PlayerNumberPacket numberOfPlayers = new Packets.PlayerNumberPacket();
        numberOfPlayers.numberOfPlayersConnected = this.numberOfPlayers;
        server.sendToAllTCP(numberOfPlayers);
    }


    public void startGameSession() {
        Packets.StartGamePackage p = new Packets.StartGamePackage();
        server.sendToAllTCP(p);
    }

    /** When something is sent to the server this method gets called and sorts
     *  out what type of message it is before it sends it all clients
     *
     * @param connection connection
     * @param object object
     */
    public void received(Connection connection, Object object) {

        if (object instanceof Packets.StartGamePackage) {

            Packets.playerInfo updatedPlayerInfo = new Packets.playerInfo();
            updatedPlayerInfo.playerInfo = playerInfoGlobal;
            updatedPlayerInfo.firstPacket = true;
            server.sendToAllTCP(updatedPlayerInfo);

            this.startGameSession();
        }

        if (object instanceof Packets.playerInfo) {
            Packets.playerInfo playerInfo = (Packets.playerInfo) object;
            Set<Integer> findPlayerIdKey = playerInfo.playerInfo.keySet();

            for (Integer key: findPlayerIdKey) {
                playerInfoGlobal.put(key, playerInfo.playerInfo.get(key));
            }
            Packets.playerInfo sendPlayerInfo = new Packets.playerInfo();
            sendPlayerInfo.playerInfo = playerInfoGlobal;
            server.sendToAllTCP(sendPlayerInfo);


        } else if (object instanceof Packets.SendAction) {
            checkIfAllClientsAreReady++;
            Packets.SendAction receivedAction = (Packets.SendAction) object;

            Set<Integer> findPlayerIdKey = receivedAction.actionList.keySet();

            for (Integer key: findPlayerIdKey) {
                cardsReceived.put(key, receivedAction.actionList.get(key));
            }
            if(checkIfAllClientsAreReady == numberOfPlayers) {
                receivedAction.actionList = cardsReceived;
                server.sendToAllTCP(receivedAction);
                cardsReceived.clear();
                checkIfAllClientsAreReady = 0;

            }
        }
    }
}
