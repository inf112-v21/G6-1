package inf112.skeleton.app.gameLogic;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.app.graphics.Graphics;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;


public class LocalHumanPlayerLogicTest {


    Graphics graphics = new Graphics();
    Lwjgl3ApplicationConfiguration cfg;

    @Before
    public void setup() {

    }

    @Test
    public void testGameIsOverIfPlayerStartsOnFlag() throws Exception {
        float flagX = 300;
        float flagY = 2700;
        cfg = new Lwjgl3ApplicationConfiguration();
        new Lwjgl3Application(graphics, cfg);
        graphics.localHumanPlayer.setPosition(graphics.localHumanPlayer.updateX = 300, graphics.localHumanPlayer.updateY = 2700);
        assertTrue(graphics.localHumanPlayer.isGameOver(graphics.localHumanPlayer.flagLayer));
    }

    @Test
    public void testGameIsNotOverIfPlayerIsNotOnFlag() throws Exception {
        cfg = new Lwjgl3ApplicationConfiguration();
        new Lwjgl3Application(graphics, cfg);
        graphics.localHumanPlayer.updatePlayerLocation(graphics.localHumanPlayer.updateX = 300, graphics.localHumanPlayer.updateY = 300);
        assertTrue(!graphics.localHumanPlayer.isPlayerOnFlag(graphics.localHumanPlayer.flagLayer));
    }


    @Test
    public void testPlayersPositionMustBeOnGameBoard() throws Exception {
        cfg = new Lwjgl3ApplicationConfiguration();
        new Lwjgl3Application(graphics, cfg);
        float xPositiveDirection = 3301;
        float xNegativeDirection = -1;
        float yPositiveDirection = 3901;
        float yNegativeDirection = -1;
        graphics.localHumanPlayer.updatePlayerLocation(graphics.localHumanPlayer.updateX = xPositiveDirection, graphics.localHumanPlayer.updateY = 0);
        Assertions.assertNotEquals(graphics.localHumanPlayer.getX(),xPositiveDirection);

        graphics.localHumanPlayer.updatePlayerLocation(graphics.localHumanPlayer.updateX = xNegativeDirection, graphics.localHumanPlayer.updateY = 0);
        Assertions.assertNotEquals(graphics.localHumanPlayer.getX(),xNegativeDirection);

        graphics.localHumanPlayer.updatePlayerLocation(graphics.localHumanPlayer.updateX = 0, graphics.localHumanPlayer.updateY = yPositiveDirection);
        Assertions.assertNotEquals(graphics.localHumanPlayer.getY(),yPositiveDirection);

        graphics.localHumanPlayer.updatePlayerLocation(graphics.localHumanPlayer.updateX = 0, graphics.localHumanPlayer.updateY = yNegativeDirection);
        Assertions.assertNotEquals(graphics.localHumanPlayer.getY(),yPositiveDirection);

    }
}
