package inf112.skeleton.app.networking;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

//Packet class, to be sent over the network
public class PacketMessage implements Runnable{

    private static Server s;

    private void plis() {

    }

    @Override
    public void run() {
        s = new Server();
        s.getKryo().register()

    }
}
