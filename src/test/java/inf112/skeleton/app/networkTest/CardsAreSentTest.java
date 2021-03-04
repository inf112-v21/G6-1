package inf112.skeleton.app.networkTest;

import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.networking.GameClient;
import inf112.skeleton.app.networking.GameServer;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;


class CardsAreSentTest {

    private static GameServer serverTest;
    private static GameClient clientTest;

    private static Game gameTest;
    private static GameClient testClient;

    @BeforeEach
    public static void ServerSetUp() {
        gameTest = new Game();
        serverTest = new GameServer("RiskyExchange.tmx");
        serverTest.run();
        clientTest = new GameClient(serverTest.getAddress(), gameTest);
    }

    @Test
    public void serverGetsCardsTest() {

    }
}