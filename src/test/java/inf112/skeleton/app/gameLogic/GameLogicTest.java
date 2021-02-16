package inf112.skeleton.app.gameLogic;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.GameLogic.GameLogic;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.*;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameLogicTest {

    Sprite playerSprite;
    GameLogic gameLogic;
    private TiledMap tiledMap;
    private TiledMapTileLayer flagLayer;
    //InstrumentationRegistry.getContext().getAssets().open(filePath);

    @BeforeEach
    public void setup() {
        playerSprite = new Sprite();
        gameLogic = new GameLogic(playerSprite, (TiledMapTileLayer) tiledMap.getLayers().get("flagLayer"));
        tiledMap = new TmxMapLoader().load("Maps/RiskyExchange.tmx");
    }



    // does not work, gameLogic points to null;
    @Test
    public void testGameIsOverIfPlayerStartsOnFlag() throws Exception {

        playerSprite = new Sprite();
        gameLogic = new GameLogic(playerSprite, (TiledMapTileLayer) tiledMap.getLayers().get("flagLayer"));
        float flagX = 300;
        float flagY = 2700;

        gameLogic.updatePlayerLocation(flagX, flagY);
        assertTrue(gameLogic.isPlayerOnFlag());
    }

    @Test
    public void testGameIsNotOverIfPlayerIsNotOnFlag() throws Exception {
        gameLogic.updatePlayerLocation(0, 0);
        assertTrue(!gameLogic.isPlayerOnFlag());
    }



    @Test
    public void testPlayersPositionMustBeOnGameBoard() throws Exception {
        float xPosetivDirection = 3301;
        float xNegativeDiretion = -1;
        float yPosetivDirection = 3901;
        float yNegativDiretion = -1;

        assertFalse(gameLogic.canPlayerMove(xPosetivDirection, 0));
        assertFalse(gameLogic.canPlayerMove(xNegativeDiretion, 0));
        assertFalse(gameLogic.canPlayerMove(0,yPosetivDirection));
        assertFalse(gameLogic.canPlayerMove(0,yPosetivDirection));

        assertTrue(gameLogic.canPlayerMove(0,0));
    }

    @Test
    public void testPlayerMovesUpWhenUpKeyIsPressed() throws Exception {


        //set player position
        float originalX = 1;
        float originalY = 1;

        gameLogic.updatePlayerLocation(originalX,originalY);

        //simulate UP key press
        try {
            Robot robot = new Robot();
            robot.keyPress(Input.Keys.UP);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        float newX = gameLogic.getX();
        float newY = gameLogic.getY();

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

        gameLogic.updatePlayerLocation(originalX,originalY);

        // simulate DOWN key press
        try {
            Robot robot = new Robot();
            robot.keyPress(Input.Keys.DOWN);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        float newX = gameLogic.getX();
        float newY = gameLogic.getY();

        //check if player position has moved by comparing the old coordinates to the new ones
        assertTrue(newY < originalY);
        assertTrue(newX == originalX);


    }

    @Test
    public void testPlayerMovesRightWhenRightKeyIsPressed() throws Exception {
        float originalX = 2;
        float originalY = 2;

        gameLogic.updatePlayerLocation(originalX,originalY);

        try {
            Robot robot = new Robot();
            robot.keyPress(Input.Keys.RIGHT);
        }  catch (AWTException e) {
            e.printStackTrace();
        }

        float newX = gameLogic.getX();
        float newY = gameLogic.getY();

        assertTrue(newX > originalX);
        assertTrue(newY == originalY);

    }

    @Test
    public void testPlayerMovesLeftWhenLeftKeyIsPressed() throws Exception {
        float originalX = 2;
        float originalY = 2;

        gameLogic.updatePlayerLocation(originalX,originalY);

        try {
            Robot robot = new Robot();
            robot.keyPress(Input.Keys.LEFT);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        float newX = gameLogic.getX();
        float newY = gameLogic.getY();

        assertTrue(newX < originalX);
        assertTrue(newY == originalY);


    }



}
