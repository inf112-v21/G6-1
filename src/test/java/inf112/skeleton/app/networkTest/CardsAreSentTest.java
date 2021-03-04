package inf112.skeleton.app.networkTest;

import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.networking.GameClient;
import inf112.skeleton.app.networking.GameServer;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class CardsAreSentTest {

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


    @Test
    public void clientGetsCardsTest() {

    }


    /**
     * Returns true if the connection is successful
     */
    @Test
    public void connectionIsSuccessfulTest() {
        assertTrue(clientTest.getC());
    }


    /**
     * closes the client and server
     */
    @AfterClass
    public static void reset() {
        serverTest.delete();
        clientTest.delete();
    }


}