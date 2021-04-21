package inf112.skeleton.app.networking.packets;

import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Action;

import java.util.ArrayList;
import java.util.HashMap;

public class Packets {

    public static class UpdateNames {
        public String[] name;
        public int playerID;
    }

    public static class CardsPacket {
        public ArrayList<Card> playedCards;
        public int playerId;
    }

    public static class PlayerNumberPacket {
        public int numberOfPlayersConnected;
    }

    public static class StartSignalPacket{
        public boolean start;
    }

    public static class ReadySignalPacket {
        public boolean signal;
        public boolean[] allReady;
    }

    public static class ShutDownRobotPacket {
        public boolean[] playersShutdown;
    }

    public static class RemovePlayerPacket {
        public int playerId;
    }

    public static class StartGamePackage {
        public boolean signal;
        public boolean[] allReady;
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


    /** Packet containing a players moves, to be sent to the server */


    /** A packet containing all player moves for a round so that clients may execute them */
    public static class RoundPacket {
        public HashMap<Integer, ArrayList<Card>> playerMoves;
        public int playerId;
    }

    static public class registerName {
        public String name;
    }

    static public class playerInfo {
        public HashMap<Integer, ArrayList<Float>> playerInfo = new HashMap<>();
    }

    static public class SendAction {
        public ArrayList<Action> actionList = new ArrayList<>();
    }



}


//

