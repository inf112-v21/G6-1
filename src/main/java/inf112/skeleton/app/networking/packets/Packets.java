package inf112.skeleton.app.networking.packets;

import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Packets {
    public static class MessagePacket {
        public String message;
        public int playerID;
    }


    public static class StartSignalPacket{
        public boolean start;
    }

    public static class ReadySignalPacket{
        public boolean signal;
        public boolean[] allReady;
    }

    public static class ShutDownRobotPacket{
        public boolean[] playersShutdown;
    }


    /** Packet containing a players moves, to be sent to the server */
    public static class CardsPacket {
        public ArrayList<Card> playedCards;
        public int playerId;
    }

    /** A packet containing all player moves for a round so that clients may execute them */
    public static class RoundPacket {
        public HashMap<Integer, ArrayList<Card>> playerMoves;
    }

    /** Package instructing all clients to start the game */
    public static class StartGamePackage {
        public boolean signal;
        public boolean[] allReady;
    }

    ////
    // Player packets
    ////

    /**
     * Packet sent to clients to remove a player with some player id
     */
    public static class RemovePlayerPacket {
        /** The player number to remove */
        public int playerId;
    }

    public static class NamePacket {
        public String[] name;
        public int playerId;
    }

    /**
     * Packet sent to players to denote which player number they are
     */
    public static class PlayerIdPacket {
        /** Your player number */
        public int playerNumber;
    }


    public static class PlayerNumberPacket {
        public int numberOfPlayers;
    }
}
