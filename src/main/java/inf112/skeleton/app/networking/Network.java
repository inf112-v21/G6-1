package inf112.skeleton.app.networking;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import inf112.skeleton.app.player.Player;

import java.util.ArrayList;


public class Network {

    static public final int TCPport = 54555;
    static public final int UDPport = 54777;

    /**
     * Method used to register all needed classes for the server and client.
     * This must be identical on both server and client.
     *
     * @param
     */
    static public void register (EndPoint endpoint) {
        Kryo kyro = endpoint.getKryo();
        kyro.register(playerList.class);
        kyro.register(addNewPlayer.class);
    }

    static public class playerList {
        public ArrayList<Player> list;
    }

    static public class addNewPlayer {
        public int number=0;
    }
}
