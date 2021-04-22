package inf112.skeleton.app.networking;

import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.networking.listeners.ServerListener;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Creates a Kryonet server.
 * The server can send and receive data from and to clients.
 * run() starts the server
 */


public class GameServer implements Runnable {

    Server server;
    ServerListener serverListener;

    public InetAddress address;
    static final int udpPort = 54777;
    static final int tcpPort = 54555;


    public GameServer(String map) {

    }


    /**
     * Starts the server, tries to bind it and fetches host-ip for further use.
     *
     */
    public void run() {
        server = new Server();
        serverListener = new ServerListener(server);
        Network.register(server);


        System.out.println("Creating the server...");
        server.addListener(serverListener);

        try {
            server.bind(tcpPort, udpPort);
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.start();

        try {
            address = InetAddress.getLocalHost();
            System.out.println(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        System.out.println("Server has started:");
    }

    public void stop() {
        server.stop();
    }

    public Server getServer() { return server; }


    public InetAddress getAddress() {
        return address;
    }

}



