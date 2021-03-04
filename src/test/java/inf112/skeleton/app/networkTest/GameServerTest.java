package inf112.skeleton.app.networkTest;

import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.networking.GameServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lwjgl.system.CallbackI;

import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.*;

class GameServerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    //Manual test
    void ServerShouldStartOnHostsComputer() {
        GameServer server = new GameServer("RiskyExchange");

        server.run();
        String correctIP = server.getAddress().getHostAddress();
        server.stop();

        assertEquals(correctIP, "192.168.68.105");

    }

    @Test
    void ChosenMapIsBeingUsedWhenHostingGame() {
        GameServer server = new GameServer("RiskyExchange");

        server.run();

        server.stop();

        assertEquals("correct map", "chosen map");
    }
}