package inf112.skeleton.app.networkTest;


import inf112.skeleton.app.networking.GameServer;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;

public class GameServerTest {


    @Test
    public void ServerShouldStartOnHostsComputer() {
        GameServer server = new GameServer();

        server.run();
        String correctIP = server.getAddress().getHostAddress();
        server.stop();

        assertEquals(correctIP, server.getAddress().getHostAddress());

    }
}