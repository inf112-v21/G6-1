package inf112.skeleton.app.networking.packets;


import inf112.skeleton.app.shared.Action;

import java.util.ArrayList;
import java.util.HashMap;

public class Packets {


    public static class PlayerNumberPacket {
        public int numberOfPlayersConnected;
    }

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

    static public class playerInfo {
        public HashMap<Integer, ArrayList<Float>> playerInfo = new HashMap<>();
        public boolean firstPacket;
    }

    static public class SendAction {
        public HashMap<Integer, ArrayList<HashMap<Integer, Action>>> actionList = new HashMap<>();
    }

    static public  class TestPack{
        public String packet;
    }



}


//

