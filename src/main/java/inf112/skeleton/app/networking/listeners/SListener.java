package inf112.skeleton.app.networking.listeners;

import com.esotericsoftware.kryonet.Server;

public class SListener {

    private String map;
    private Server server;

    public SListener(Server server, String map) {
        this.server = server;
        this.map = map;
    }
}
