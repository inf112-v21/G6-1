package inf112.skeleton.app.player;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;
import org.junit.Test;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class playerLogicTest {

    /**
     * In order for the test to run, a game window must be initialised.
     * When the game window is manually closed, the tests will run as expected.
     * The tests must be run individually.
     * If you get error "ExceptionInInitializerError", run the JVM with -XstartOnFirstThread.
     */
    Graphics graphics = new Graphics(new Game());
    Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
    HumanPlayer humanPlayer = new HumanPlayer(Direction.NORTH, 1, Color.GREEN);

    public playerLogicTest() {
        new Lwjgl3Application(graphics, cfg);
    }

    @Test
    public void testGameIsOverIfPlayerStartsOnFlag() {
        humanPlayer.updatePlayerXPosition(300);
        humanPlayer.updatePlayerYPosition(2700);

        assertTrue(humanPlayer.isPlayerOnFlag((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer")));
    }

    @Test
    public void testGameIsNotOverIfPlayerIsNotOnFlag() {
        humanPlayer.updatePlayerXPosition(300);
        humanPlayer.updatePlayerYPosition(300);
        Assertions.assertFalse(humanPlayer.isPlayerOnFlag((TiledMapTileLayer)graphics.tiledMap.getLayers().get("flagLayer")));
    }

    @Test
    public void testPlayersPositionMustBeOnGameBoard() {
        float xPositiveDirection = 3301;
        float xNegativeDirection = -1;
        float yPositiveDirection = 3901;
        float yNegativeDirection = -1;

        Assertions.assertFalse(humanPlayer.canPlayerMove(xPositiveDirection, 0));
        Assertions.assertFalse(humanPlayer.canPlayerMove(xNegativeDirection, 0));
        Assertions.assertFalse(humanPlayer.canPlayerMove(0, yPositiveDirection));
        Assertions.assertFalse(humanPlayer.canPlayerMove(0, yNegativeDirection));
    }

}
