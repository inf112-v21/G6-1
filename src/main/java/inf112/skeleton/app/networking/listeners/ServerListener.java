package inf112.skeleton.app.networking.listeners;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

// Listener class for receiving and sending data from clients to all clients.
public class ServerListener extends Listener {
    private final String map;
    private Server server;
    private int playerNumber = 1;


    /**
     *
     * @param server Kryonet server that is being used
     * @param map
     */
    public ServerListener(Server server, String map) {
        this.server = server;
        this.map = map;

    }



    public void playerIsConnected(Connection connection) {
        System.out.println("Player number " + playerNumber + " has connected to the server");
        playerNumber++;
        Packets.Packet
    }
}
