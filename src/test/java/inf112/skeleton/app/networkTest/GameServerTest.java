package inf112.skeleton.app.networkTest;


import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.networking.GameClient;
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

    @Test
    public void ClientCanConnectToHostLocally(){
        GameServer server = new GameServer();
        Game game = new Game();

        server.run();
        GameClient client = new GameClient(server.getAddress(), game);
        int correctID = client.client.getID();
        server.stop();
        client.client.stop();

        assertEquals(1, correctID);
    }

}