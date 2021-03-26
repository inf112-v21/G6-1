package inf112.skeleton.app.boardItem;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.BoardItems.Gear;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * In order for the test to run, a game window must be initialised.
 * When the game window is manually closed, the tests will run as expected.
 * If you get error "ExceptionInInitializerError", run the JVM with -XstartOnFirstThread.
 */

public class gearTest {

    Lwjgl3ApplicationConfiguration cfg;
    Graphics graphics;
    HumanPlayer humanPlayer;
    Gear gear;
    ArrayList<Player> testPlayers = new ArrayList<>();
    int tileSize = 300;

    @Before
    public void setup() {
        graphics = new Graphics(new Game());
        cfg = new Lwjgl3ApplicationConfiguration();
        humanPlayer = new HumanPlayer(Direction.NORTH, 1, Color.GREEN);
        gear = new Gear();
        testPlayers.add(humanPlayer);
        new Lwjgl3Application(graphics, cfg);
    }
    @Test
    public void playerRotatesLeftWhenStoodOnRedGear(){
        humanPlayer.setPlayerStartXPosition(3*tileSize);
        humanPlayer.setPlayerStartYPosition(5*tileSize);

        gear.runGears(testPlayers, (TiledMapTileLayer) graphics.tiledMap.getLayers().get("RedGear"),
                (TiledMapTileLayer) graphics.tiledMap.getLayers().get("GreenGear"));

        Assertions.assertEquals(Direction.WEST, humanPlayer.direction);
    }

    @Test
    public void playerRotatesRightWhenStoodOnGreenGear(){
        humanPlayer.setPlayerStartXPosition(10*tileSize);
        humanPlayer.setPlayerStartYPosition(3*tileSize);

        gear.runGears(testPlayers, (TiledMapTileLayer) graphics.tiledMap.getLayers().get("RedGear"),
                (TiledMapTileLayer) graphics.tiledMap.getLayers().get("GreenGear"));

        Assertions.assertEquals(Direction.EAST, humanPlayer.direction);
    }

    @Test
    public void playerDoesNotMoveWhenOnGear(){
        humanPlayer.setPlayerStartXPosition(10*tileSize);
        humanPlayer.setPlayerStartYPosition(3*tileSize);

        gear.runGears(testPlayers, (TiledMapTileLayer) graphics.tiledMap.getLayers().get("RedGear"),
                (TiledMapTileLayer) graphics.tiledMap.getLayers().get("GreenGear"));

        Assertions.assertEquals(10*tileSize, humanPlayer.getPlayerXPosition());
        Assertions.assertEquals(3*tileSize, humanPlayer.getPlayerYPosition());
    }
}
