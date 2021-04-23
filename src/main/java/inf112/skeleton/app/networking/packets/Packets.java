package inf112.skeleton.app.networking.packets;


import inf112.skeleton.app.shared.Action;

import java.util.ArrayList;
import java.util.HashMap;

public class Packets {


    public static class PlayerNumberPacket {
        public int numberOfPlayersConnected;
    }

    /**
     * Used to be sent as an empty packet to tell clients to start the game instance
     *
     */
    public static class StartGamePackage {
    }


    public static class SendMapNameToPlayer {
        public Object map;
    }

    /**
     * Packet sent to clients to remove a player with some player id
     */
    public static class PlayerIdPacket {
        public int playerNumber;
    }

    /**
     * Important packet to send the id of players alongside coordinates to server, where server returns
     * a list of all the players connected for the clients to use at their local created game
     *
     */
    static public class playerInfo {
        public HashMap<Integer, ArrayList<Float>> playerInfo = new HashMap<>();
        public boolean firstPacket;
    }

    /**
     * Packet to send which cards the player has selected. When server receives from all clients, the clients
     * gets an updated hashmap of all players moves.
     *
     */
    static public class SendAction {
        public HashMap<Integer, ArrayList<HashMap<Integer, Action>>> actionList = new HashMap<>();
    }




}


//

