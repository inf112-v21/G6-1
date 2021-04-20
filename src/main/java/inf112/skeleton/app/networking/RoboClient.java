package inf112.skeleton.app.networking;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.player.Player;


import java.io.IOException;


public class RoboClient extends Listener {
    private static Client client;
    private final Game game;
    private String ip;
    private Player myPlayer=null;
    private Boolean readyForNextPlay = true;

    /**
     * Creates the client object, consists of a listener class created within the object.
     * received method will listen for packets being sent from the server to all clients.
     *
     * @param game
     * @param ip
     * @throws IOException
     */

    public RoboClient(Game game, String ip) throws IOException {
        this.ip = ip;
        this.game = game;

        client = new Client();
        Network.register(client);
        new Thread(client).start();

        client.addListener(new Listener.ThreadedListener(new Listener()) {
            public void connected(Connection connection) {
            }

            public void received(Connection connection, Object object) {
                if (object instanceof Network.playerInfo && myPlayer == null) {
                    Network.playerInfo myPlayerObject = (Network.playerInfo) object;
                    myPlayer = myPlayerObject.list.get(-1);
                } else if (object instanceof Network.playerInfo) {
                    Network.playerInfo updatedPlayerList = (Network.playerInfo)object;
                    game.players = updatedPlayerList.list;
                    readyForNextPlay = true;

                }

                if (object instanceof Network.playerHasChosenCards){
                    sendUpdatedPlayerObject();
                }
            }

            public void disconnected(Connection connection) {
                System.exit(0);
            }
        });
        try {
            client.connect(5000, ip, Network.TCPport, Network.UDPport);
            Network.addNewPlayer newPlayer = new Network.addNewPlayer();
            newPlayer.bool = true;
            client.sendTCP(newPlayer);

            while(true){
                Thread.sleep(100);
                if(readyForNextPlay) {
                    if(sendCardsReadyToServer()) {
                        sendUpdatedPlayerObject();
                        readyForNextPlay = false;
                    }
                }
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * To be used in loop, needs to wait for package back from server before going past this package
     */
    public boolean sendCardsReadyToServer () {
        if(myPlayer.ready){
            Network.playerHasChosenCards readySignal = new Network.playerHasChosenCards();
            readySignal.bool = true;
            client.sendTCP(readySignal);
            return true;
        }
        return false;
    }
    public void sendUpdatedPlayerObject () {
        Network.playerInfo myPlayerInfo = new Network.playerInfo();
        myPlayerInfo.player = myPlayer;
        client.sendTCP(myPlayerInfo);
    }


}