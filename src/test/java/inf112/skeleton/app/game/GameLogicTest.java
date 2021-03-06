package inf112.skeleton.app.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class GameLogicTest {

    /**
     * In order for the test to run, a game window must be initialised.
     * When the game window is manually closed, the tests will run as expected.
     * If you get error "ExceptionInInitializerError", run the JVM with -XstartOnFirstThread.
     */

    Lwjgl3ApplicationConfiguration cfg;
    Graphics graphics;
    HumanPlayer humanPlayer;
    final int tileSize = 300;


    @Before
    public void setup() {
        graphics = new Graphics(new Game());
        cfg = new Lwjgl3ApplicationConfiguration();
        humanPlayer = new HumanPlayer(Direction.NORTH, 1, Color.GREEN);
        new Lwjgl3Application(graphics, cfg);
    }

    @Test
    public void doesPlayerWinWhenVisitingFlagsInCorrectOrder(){
        //Flag 1
        humanPlayer.setPlayerStartXPosition(7*tileSize);
        humanPlayer.setPlayerStartYPosition(12*tileSize);

        humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer"));

        //Flag 2
        humanPlayer.updatePlayerXPosition(9*tileSize);
        humanPlayer.updatePlayerYPosition(6*tileSize);

        humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer"));

        //Flag 3
        humanPlayer.updatePlayerXPosition(tileSize);
        humanPlayer.updatePlayerYPosition(9*tileSize);

        humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer"));

        Assertions.assertTrue(humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer")));

    }

    @Test
    public void doesPlayerNotWhenVisitingFlagsInIncorrectOrder(){
        //Flag 2
        humanPlayer.updatePlayerXPosition(9*tileSize);
        humanPlayer.updatePlayerYPosition(6*tileSize);

        humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer"));

        //Flag 1
        humanPlayer.setPlayerStartXPosition(7*tileSize);
        humanPlayer.setPlayerStartYPosition(12*tileSize);

        humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer"));

        //Flag 3
        humanPlayer.updatePlayerXPosition(tileSize);
        humanPlayer.updatePlayerYPosition(9*tileSize);

        humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer"));

        Assertions.assertFalse(humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer")));

    }

    @Test
    public void doesPlayerWinIfAllFlagsAreVisitedFlags1323() {
        //Flag 1
        humanPlayer.setPlayerStartXPosition(7*tileSize);
        humanPlayer.setPlayerStartYPosition(12*tileSize);

        humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer"));

        //Flag 3
        humanPlayer.updatePlayerXPosition(tileSize);
        humanPlayer.updatePlayerYPosition(9*tileSize);

        humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer"));

        //Flag 2
        humanPlayer.updatePlayerXPosition(9*tileSize);
        humanPlayer.updatePlayerYPosition(6*tileSize);

        humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer"));

        //Flag 3
        humanPlayer.updatePlayerXPosition(tileSize);
        humanPlayer.updatePlayerYPosition(9*tileSize);

        humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer"));

        Assertions.assertTrue(humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer")));




    }
}
