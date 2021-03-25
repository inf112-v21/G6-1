package inf112.skeleton.app.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.BoardItems.Conveyor;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class gameLogicTest {

    Lwjgl3ApplicationConfiguration cfg;
    Graphics graphics;
    HumanPlayer humanPlayer;


    @Before
    public void setup() {
        graphics = new Graphics(new Game());
        cfg = new Lwjgl3ApplicationConfiguration();
        humanPlayer = new HumanPlayer(Direction.NORTH, 1, Color.GREEN);
        new Lwjgl3Application(graphics, cfg);
    }

    @Test
    public void doesPlayerWinWhenVisitingFlagsInCorrectOrder(){
        humanPlayer.setPlayerStartXPosition(7*300);
        humanPlayer.setPlayerStartYPosition(12*300);

        humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer"));

        humanPlayer.updatePlayerXPosition(9*300);
        humanPlayer.updatePlayerYPosition(6*300);

        humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer"));

        humanPlayer.updatePlayerXPosition(1*300);
        humanPlayer.updatePlayerYPosition(9*300);

        humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer"));

        Assertions.assertEquals(true, humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer")));

    }

    @Test
    public void doesPlayerNotWhenVisitingFlagsInIncorrectOrder(){
        humanPlayer.setPlayerStartXPosition(7*300);
        humanPlayer.setPlayerStartYPosition(12*300);

        humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer"));

        humanPlayer.updatePlayerXPosition(9*300);
        humanPlayer.updatePlayerYPosition(6*300);

        humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer"));

        humanPlayer.updatePlayerXPosition(1*300);
        humanPlayer.updatePlayerYPosition(9*300);

        humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer"));

        Assertions.assertEquals(true, humanPlayer.hasPlayerVisitedAllFlags((TiledMapTileLayer) graphics.tiledMap.getLayers().get("flagLayer")));

    }
}
