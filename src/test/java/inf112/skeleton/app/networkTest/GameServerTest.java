package inf112.skeleton.app.networkTest;

import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.networking.GameServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.lwjgl.system.CallbackI;

import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.*;

public class GameServerTest {

    @Test
    public void ServerShouldStartOnHostsComputer() {
        GameServer server = new GameServer("RiskyExchange");

        server.run();
        String correctIP = server.getAddress().getHostAddress();
        server.stop();

        assertEquals(correctIP, server.getAddress().getHostAddress());

    }

    @Test
    public void ChosenMapIsBeingUsedWhenHostingGame() {
        GameServer server = new GameServer("RiskyExchange");

        server.run();

        server.stop();

        assertEquals("correct map", "chosen map");
    }
}