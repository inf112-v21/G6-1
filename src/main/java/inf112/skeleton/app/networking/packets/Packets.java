package inf112.skeleton.app.networking.packets;

public class Packets {
    public static class MessagePacket {
        public String message;
        public int playerID;
    }

    public static class CardsPacket{
        public int[][] playedCards;
        public int playerId;
    }
    public static class PlayerNumberPacket{
        public int playerNumber;
    }

    public static class StartSignalPacket{
        public boolean start;
    }

    public static class NamePacket{
        public String[] name;
        public int playerId;
    }

    public static class ReadySignalPacket{
        public boolean signal;
        public boolean[] allReady;
    }

    public static class ShutDownRobotPacket{
        public boolean[] playersShutdown;
    }

    public static class RemovePlayerPacket {
        public int playerId;
    }

}
