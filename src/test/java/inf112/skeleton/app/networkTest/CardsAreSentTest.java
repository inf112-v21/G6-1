package inf112.skeleton.app.networkTest;

import inf112.skeleton.app.card.*;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.networking.GameClient;
import inf112.skeleton.app.networking.GameServer;
import inf112.skeleton.app.shared.Action;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
//import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class CardsAreSentTest {

    private static GameServer serverTest;
    private static GameClient clientTest;

    private static Game gameTest;
    private static GameClient testClient;

    @BeforeEach
    public static void ServerSetUp() {
        //gameTest = mock(Game.class);
        serverTest = new GameServer("RiskyExchange.tmx");
        serverTest.run();
        clientTest = new GameClient(serverTest.getAddress(), gameTest);
    }

    @Test
    public void serverGetsCardsTest() {
        MoveOne moveOneCard = new MoveOne(490, Action.MOVE_ONE);
        MoveTwo moveTwoCard = new MoveTwo(670, Action.MOVE_TWO);
        MoveThree moveThreeCard = new MoveThree(790, Action.MOVE_THREE);
        RotateRight rotateRightCard = new RotateRight(80, Action.ROTATE_RIGHT);
        RotateLeft rotateLeftCard = new RotateLeft(70, Action.ROTATE_LEFT);
        BackUp backUpCard = new BackUp(430, Action.BACK_UP);
        UTurn uTurnCard = new UTurn(10, Action.U_TURN);
        //Card[] cards1 = {moveOneCard,moveTwoCard,moveThreeCard,rotateRightCard,rotateLeftCard,backUpCard,uTurnCard};
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(moveOneCard);
        cards.add(moveTwoCard);
        cards.add(moveThreeCard);
        cards.add(rotateRightCard);
        cards.add(rotateLeftCard);
        cards.add(backUpCard);
        cards.add(uTurnCard);

        clientTest.sendCardsToServer(cards);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < cards.size(); i++) {
            assertEquals(cards.get(i), serverTest.getCardsLastUsed().get(i));
        }

    }


    @Test
    public void clientGetsCardsTest() {
        MoveOne moveOneCard = new MoveOne(490, Action.MOVE_ONE);
        MoveTwo moveTwoCard = new MoveTwo(670, Action.MOVE_TWO);
        MoveThree moveThreeCard = new MoveThree(790, Action.MOVE_THREE);
        RotateRight rotateRightCard = new RotateRight(80, Action.ROTATE_RIGHT);
        RotateLeft rotateLeftCard = new RotateLeft(70, Action.ROTATE_LEFT);
        BackUp backUpCard = new BackUp(430, Action.BACK_UP);
        UTurn uTurnCard = new UTurn(10, Action.U_TURN);
        //Card[] cards1 = {moveOneCard,moveTwoCard,moveThreeCard,rotateRightCard,rotateLeftCard,backUpCard,uTurnCard};
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(moveOneCard);
        cards.add(moveTwoCard);
        cards.add(moveThreeCard);
        cards.add(rotateRightCard);
        cards.add(rotateLeftCard);
        cards.add(backUpCard);
        cards.add(uTurnCard);
        clientTest.sendCardsToServer(cards);

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < cards.size(); i++) {
            assertEquals(cards.get(i), clientTest.getLastCardsSent().get(i));
        }
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