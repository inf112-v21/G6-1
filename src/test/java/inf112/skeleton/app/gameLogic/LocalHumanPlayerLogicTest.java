package inf112.skeleton.app.gameLogic;



import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.player.LocalHumanPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;


public class LocalHumanPlayerLogicTest {


    Graphics graphics = new Graphics();
    Lwjgl3ApplicationConfiguration cfg;

    @BeforeEach
    public void setup() {
    }


    // does not work, gameLogic points to null;
    @Test
    public void testGameIsOverIfPlayerStartsOnFlag() throws Exception {
        float flagX = 300;
        float flagY = 2700;

        cfg = new Lwjgl3ApplicationConfiguration();
        //cfg.setInitialVisible(true);
        new Lwjgl3Application(graphics, cfg);
        graphics.localHumanPlayer.setPosition(300,2700);
        graphics.localHumanPlayer.setPosition(graphics.localHumanPlayer.updateX = 300, graphics.localHumanPlayer.updateY = 2700);
        assertTrue(graphics.localHumanPlayer.isGameOver(graphics.localHumanPlayer.flagLayer));
    }
/*
    @Test
    public void testGameIsNotOverIfPlayerIsNotOnFlag() throws Exception {
        playerLogic.updatePlayerLocation(0, 0);
        assertTrue(!playerLogic.isPlayerOnFlag());
    }



    @Test
    public void testPlayersPositionMustBeOnGameBoard() throws Exception {
        float xPosetivDirection = 3301;
        float xNegativeDiretion = -1;
        float yPosetivDirection = 3901;
        float yNegativDiretion = -1;

        assertFalse(playerLogic.canPlayerMove(xPosetivDirection, 0));
        assertFalse(playerLogic.canPlayerMove(xNegativeDiretion, 0));
        assertFalse(playerLogic.canPlayerMove(0,yPosetivDirection));
        assertFalse(playerLogic.canPlayerMove(0,yPosetivDirection));

        assertTrue(playerLogic.canPlayerMove(0,0));
    }

    @Test
    public void testPlayerMovesUpWhenUpKeyIsPressed() throws Exception {


        //set player position
        float originalX = 1;
        float originalY = 1;

        playerLogic.updatePlayerLocation(originalX,originalY);

        //simulate UP key press
        try {
            Robot robot = new Robot();
            robot.keyPress(Input.Keys.UP);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        float newX = playerLogic.getX();
        float newY = playerLogic.getY();

        //KeyEvent key = new KeyEvent(instance, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');
        //instance.getKeyListeners()[0].keyPressed(key);

        //check if player position has moved by comparing the old coordinates to the new ones
        assertTrue(newY > originalY);
        assertTrue(newX == originalX);


    }

    @Test
    public void testPlayerMovesDownWhenUpDownIsPressed() throws Exception {
        float originalX = 2;
        float originalY = 2;

        playerLogic.updatePlayerLocation(originalX,originalY);

        // simulate DOWN key press
        try {
            Robot robot = new Robot();
            robot.keyPress(Input.Keys.DOWN);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        float newX = playerLogic.getX();
        float newY = playerLogic.getY();

        //check if player position has moved by comparing the old coordinates to the new ones
        assertTrue(newY < originalY);
        assertTrue(newX == originalX);


    }

    @Test
    public void testPlayerMovesRightWhenRightKeyIsPressed() throws Exception {
        float originalX = 2;
        float originalY = 2;

        playerLogic.updatePlayerLocation(originalX,originalY);

        try {
            Robot robot = new Robot();
            robot.keyPress(Input.Keys.RIGHT);
        }  catch (AWTException e) {
            e.printStackTrace();
        }

        float newX = playerLogic.getX();
        float newY = playerLogic.getY();

        assertTrue(newX > originalX);
        assertTrue(newY == originalY);

    }

    @Test
    public void testPlayerMovesLeftWhenLeftKeyIsPressed() throws Exception {
        float originalX = 2;
        float originalY = 2;

        playerLogic.updatePlayerLocation(originalX,originalY);

        try {
            Robot robot = new Robot();
            robot.keyPress(Input.Keys.LEFT);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        float newX = playerLogic.getX();
        float newY = playerLogic.getY();

        assertTrue(newX < originalX);
        assertTrue(newY == originalY);


    }


*/
}
