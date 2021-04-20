package inf112.skeleton.app.networking;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.player.Player;

import java.io.IOException;
import java.util.ArrayList;

public class RoboServer extends Listener{
    private static Server server;
    private final Game game;
    private ArrayList<Player> playerList;
    private int giveClientIdNumber=0;
    private int checkIfAllClientIsReady=0;


    public RoboServer(final Game game) extends Listener {
        this.game = game;
        server = new Server();
        playerList = new ArrayList<>();


        System.out.println("Server is attempting to start...");
        Network.register(server);


        server.addListener(new Listener() {
                               public void connected (Connection connection) {}
                               public void received (Connection connection, Object object) {

                                   if (object instanceof Network.addNewPlayer) {
                                       Network.playerInfo playerToBeSentToNewClient = (Network.playerInfo)object;
                                       playerList.add(game.createPlayer(giveClientIdNumber));
                                       playerToBeSentToNewClient.list = playerList;
                                       server.sendToAllTCP(playerToBeSentToNewClient);
                                       giveClientIdNumber++;
                                   }

                                   if (object instanceof Network.playerHasChosenCards) {
                                       checkIfAllClientIsReady++;
                                       if(checkIfAllClientIsReady == giveClientIdNumber) {
                                           Network.playerHasChosenCards receivedReadyFromClients = (Network.playerHasChosenCards)object;
                                           receivedReadyFromClients.bool = true;
                                           checkIfAllClientIsReady = 0;
                                           playerList.clear();
                                           server.sendToAllTCP(receivedReadyFromClients);
                                       }
                                   }

                                   if (object instanceof Network.playerInfo) {
                                       Network.playerInfo addUpdatedPlayerInfo = (Network.playerInfo)object;
                                       playerList.add(addUpdatedPlayerInfo.player);
                                       checkIfAllClientIsReady++;
                                       if(checkIfAllClientIsReady == giveClientIdNumber) {
                                           Network.playerInfo sendUpdatedPlayerListToClients = new Network.playerInfo();
                                           sendUpdatedPlayerListToClients.list = playerList;
                                           server.sendToAllTCP(sendUpdatedPlayerListToClients);
                                       }
                                   }
                               }
                               public void disconnected (Connection connection) {
                               }
                           }
        );
        server.bind(Network.TCPport, Network.UDPport);
        new Thread(server).start();
    }
}


