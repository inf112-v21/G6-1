package inf112.skeleton.app.boardItem;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.BoardItems.Conveyor;
import inf112.skeleton.app.BoardItems.Gear;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;
import inf112.skeleton.app.graphics.Graphics;
import org.junit.jupiter.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class gearTest {

    Lwjgl3ApplicationConfiguration cfg;
    Graphics graphics;
    HumanPlayer humanPlayer;
    Gear gear;
 //   TiledMap tiledMap;
    ArrayList<Player> testPlayers = new ArrayList<>();


    //Usikker på hvor mye at dette man trenger, tar refactoring etterpå
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
        humanPlayer.setPlayerStartXPosition(3*300);
        humanPlayer.setPlayerStartYPosition(5*300);

       // gear.locatePlayersOnGear(testPlayers, 3*300, 5*300, 270);

        gear.findAndRunGear(testPlayers, (TiledMapTileLayer) graphics.tiledMap.getLayers().get("RedGear"),
                (TiledMapTileLayer) graphics.tiledMap.getLayers().get("GreenGear"));

        Assertions.assertEquals(Direction.WEST, humanPlayer.direction);
    }

    @Test
    public void playerRotatesRightWhenStoodOnGreenGear(){
        humanPlayer.setPlayerStartXPosition(10*300);
        humanPlayer.setPlayerStartYPosition(3*300);

        gear.findAndRunGear(testPlayers, (TiledMapTileLayer) graphics.tiledMap.getLayers().get("RedGear"),
                (TiledMapTileLayer) graphics.tiledMap.getLayers().get("GreenGear"));

        Assertions.assertEquals(Direction.EAST, humanPlayer.direction);
    }

    @Test
    public void playerDoesNotMoveWhenOnGear(){
        humanPlayer.setPlayerStartXPosition(10*300);
        humanPlayer.setPlayerStartYPosition(3*300);

        gear.findAndRunGear(testPlayers, (TiledMapTileLayer) graphics.tiledMap.getLayers().get("RedGear"),
                (TiledMapTileLayer) graphics.tiledMap.getLayers().get("GreenGear"));

        Assertions.assertEquals(10*300, humanPlayer.getPlayerXPosition());
        Assertions.assertEquals(3*300, humanPlayer.getPlayerYPosition());
    }
}
