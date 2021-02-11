package gameLogic;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.GameLogic.GameLogic;
import inf112.skeleton.app.game.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.awt.*;
import java.awt.event.KeyEvent;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameLogicTest {
    Sprite playerSprite;
    GameLogic gameLogic;


    @Before
    public void setup() {
        playerSprite = new Sprite();
        gameLogic = new GameLogic(playerSprite, new Game());
    }


    @Test
    public void testGameIsOverIfPlayerStartsOnFlag() throws Exception {
        int flagX = 1;
        int flagY = 9; //TODO find actual coordinates

        // TODO translate flag coord to pixel coords which are much larger
        //  i.e. if each tile is 32px then tile (1,9) becomes (32, 288)

        gameLogic.setPlayerStartPosition(flagX, flagY);
        assertTrue(gameLogic.isGameOver());
    }

    @Test
    public void testGameIsNotOverIfPlayerIsNotOnFlag() throws Exception {
        gameLogic.setPlayerStartPosition(0, 0);
        assertTrue(!gameLogic.isGameOver());
    }

    @Test
    public void testPlayersPositionMustBeOnGameBoard() throws Exception {

    }

    @Test
    public void testPlayerMovesUpWhenUpKeyIsPressed() throws Exception {
        //set player position
        float originalX = 1;
        float originalY = 1;

        gameLogic.setPlayerStartPosition(originalX,originalY);

        //simulate key press
        try {
            Robot robot = new Robot();
            robot.keyPress(Input.Keys.UP);
        } catch (Exception e) {

        }

        float newX = gameLogic.getX();
        float newY = gameLogic.getY();

        //KeyEvent key = new KeyEvent(instance, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');
        //instance.getKeyListeners()[0].keyPressed(key);

        //check if player position has moved
        assertTrue(newY > originalY);
        assertTrue(newX == originalX);

    }

    @Test
    public void testPlayerMovesDownWhenUpDownIsPressed() throws Exception {

    }

    @Test
    public void testPlayerMovesRightWhenRightKeyIsPressed() throws Exception {

    }

    @Test
    public void testPlayerMovesLeftWhenLeftKeyIsPressed() throws Exception {

    }

}
