package inf112.skeleton.app.networking;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import inf112.skeleton.app.networking.packets.Packets;

public class Network {


    /**
     * Method used to register all needed classes for the server and client.
     * This must be identical on both server and client.
     *
     * @param
     */
    static public void register (EndPoint endpoint) {
        Kryo kryo = endpoint.getKryo();

        kryo.register(Packets.PlayerIdPacket.class);
        kryo.register(Packets.PlayerNumberPacket.class);
        kryo.register(Packets.StartGamePackage.class);
        kryo.register(Packets.StartGamePackage.class);
        kryo.register(Packets.SendMapNameToPlayer.class);
        kryo.register(Packets.playerInfo.class);
        kryo.register(Packets.SendAction.class);

        kryo.register(java.util.HashMap.class);
        kryo.register(java.util.ArrayList.class);
        kryo.register(int[].class);

    }

}
