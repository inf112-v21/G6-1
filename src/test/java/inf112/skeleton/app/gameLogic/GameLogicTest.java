package inf112.skeleton.app.gameLogic;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.GameLogic.GameLogic;
import org.junit.Before;
import org.junit.Test;
//import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.*;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameLogicTest {

    Sprite playerSprite;
    GameLogic gameLogic;
    private TiledMapTileLayer flagLayer;
    private TiledMap tiledMap= new TmxMapLoader().load("..Assets/Maps/RiskyExchange.tmx");
    //InstrumentationRegistry.getContext().getAssets().open(filePath);
    @Before
    public void setup() {
        playerSprite = new Sprite();
        gameLogic = new GameLogic(playerSprite, (TiledMapTileLayer) tiledMap.getLayers().get("flagLayer"));
    }
/*
    @Test
    public void testGameIsOverIfPlayerStartsOnFlag() throws Exception {
        float flagX = 300;
        float flagY = 2700; //TODO find actual coordinates

        // TODO translate flag coordinates to pixel coords which are much larger
        //  i.e. if each tile is 32px then tile (1,9) becomes (32, 288)

        gameLogic.setPlayerStartPosition(flagX, flagY);
        assertTrue(gameLogic.isGameOver());
    }

    @Test
    public void testGameIsNotOverIfPlayerIsNotOnFlag() throws Exception {
        gameLogic.setPlayerStartPosition(0, 0);
        assertTrue(!gameLogic.isGameOver());
    }
*/
    @Test
    public void testPlayersPositionMustBeOnGameBoard() throws Exception {
        float xPosetivDirection = 3301;
        float xNegativeDiretion = -1;
        float yPosetivDirection = 3901;
        float yNegativDiretion = -1;

        assertFalse(gameLogic.canPlayerMove(xPosetivDirection, 0));
        assertFalse(gameLogic.canPlayerMove(xNegativeDiretion, 0));
        assertFalse(gameLogic.canPlayerMove(yPosetivDirection,0));
        assertFalse(gameLogic.canPlayerMove(yNegativDiretion,0));

        assertTrue(gameLogic.canPlayerMove(0,0));
    }

    @Test
    public void testPlayerMovesUpWhenUpKeyIsPressed() throws Exception {
        /** TODO Her får vi problemer pga i GameLogic ligger det klassevariabler som setter spiller til null igjen.
         *  SetPlayerStart position må fikses på*/
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
