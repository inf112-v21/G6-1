package inf112.skeleton.app.player;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.shared.Direction;
import org.junit.Test;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class playerLogicTest {
    Graphics graphics = new Graphics();
    Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
    HumanPlayer humanPlayer = new HumanPlayer(Direction.NORTH, 1,"Super mario");

    public playerLogicTest() {
        new Lwjgl3Application(graphics, cfg);
    }

    @Test
    public void testGameIsOverIfPlayerStartsOnFlag() {
        graphics.humanPlayer.updatePlayerXPosition(300);
        graphics.humanPlayer.updatePlayerYPosition(2700);

        assertTrue(graphics.humanPlayer.isPlayerOnFlag((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer")));


    }

    @Test
    public void testGameIsNotOverIfPlayerIsNotOnFlag() {
        graphics.humanPlayer.updatePlayerXPosition(300);
        graphics.humanPlayer.updatePlayerYPosition(300);
        Assertions.assertFalse(graphics.humanPlayer.isPlayerOnFlag((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer")));
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
