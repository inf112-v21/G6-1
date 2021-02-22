package inf112.skeleton.app.networking;

import com.esotericsoftware.kryonet.Client;

import java.io.IOException;

public class NetworkClient extends Thread{
    static Client client;
    static String ip = "localhost";
    static int udpPort = 54777, tcpPort = 54555;
    static boolean messageReceived = false;

    public void runClient() throws IOException {
        client = new Client();
        client.start();
        client.connect(10000, "192.168.0.4", 54555, 54777);



    }

}
