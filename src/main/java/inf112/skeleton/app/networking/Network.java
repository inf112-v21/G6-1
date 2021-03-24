package inf112.skeleton.app.networking;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import inf112.skeleton.app.networking.packets.Packets;

//In this class, common methods for both Server and Client will be implemented.
public class Network {

    /**
     * Method used to register all needed classes for the server and client.
     * This must be identical on both server and client.
     *
     * @param
     */
    static public void register (EndPoint endpoint) {
        Kryo kyro = endpoint.getKryo();

        kyro.register(Packets.CardsPacket.class);
        kyro.register(Packets.NamePacket.class);
        kyro.register(Packets.PlayerIdPacket.class);
        kyro.register(Packets.PlayerNumberPacket.class);
        kyro.register(Packets.StartGamePackage.class);
        kyro.register(Packets.registerName.class);
        kyro.register(Packets.RemovePlayerPacket.class);
        kyro.register(Packets.RoundPacket.class);
        kyro.register(Packets.ShutDownRobotPacket.class);
        kyro.register(Packets.StartGamePackage.class);
        kyro.register(Packets.StartSignalPacket.class);
        kyro.register(Packets.UpdateNames.class);
        kyro.register(Packets.SendMapNameToPlayer.class);


        kyro.register(String[].class);
        kyro.register(int[].class);

    }

}
