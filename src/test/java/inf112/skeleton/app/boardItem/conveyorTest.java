package inf112.skeleton.app.boardItem;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;
import inf112.skeleton.app.BoardItems.Conveyor;
import inf112.skeleton.app.graphics.Graphics;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;


/**
 * In order for the test to run, a game window must be initialised.
 * When the game window is manually closed, the tests will run as expected.
 * The tests must be run individually.
 * If you get error "ExceptionInInitializerError", run the JVM with -XstartOnFirstThread.
 */

public class conveyorTest {


    Lwjgl3ApplicationConfiguration cfg;
    Graphics graphics;
    HumanPlayer humanPlayer;
    Conveyor conveyor;


    @Before
    public void setup() {
        graphics = new Graphics(new Game());
        cfg = new Lwjgl3ApplicationConfiguration();
        humanPlayer = new HumanPlayer(Direction.NORTH, 1, Color.GREEN);
        conveyor = new Conveyor();
        new Lwjgl3Application(graphics, cfg);
    }

    @Test
    public void doesPlayerMoveOneTileOnYellowConveyor(){
        humanPlayer.setPlayerStartXPosition(4*300);
        humanPlayer.setPlayerStartYPosition(3*300);
        conveyor.movePlayerOnConveyor(humanPlayer, 0, 1*300);

        Assertions.assertEquals(4*300, humanPlayer.getPlayerXPosition());
        Assertions.assertEquals(4*300, humanPlayer.getPlayerYPosition());
    }

    @Test
    public void doesPlayerMoveTwoTileOnBlueConveyor(){
        humanPlayer.setPlayerStartXPosition(6*300);
        humanPlayer.setPlayerStartYPosition(5*300);
        conveyor.movePlayerOnConveyor(humanPlayer, 180, 2*300);

        Assertions.assertEquals(6*300, humanPlayer.getPlayerXPosition());
        Assertions.assertEquals(3*300, humanPlayer.getPlayerYPosition());
    }

    @Test
    public void doesPlayerMoveOneAtEndOfBlueConveyor(){
        humanPlayer.setPlayerStartXPosition(10*300);
        humanPlayer.setPlayerStartYPosition(8*300);
        conveyor.movePlayerOnConveyor(humanPlayer, 90, 2*300);

        Assertions.assertEquals(11*300, humanPlayer.getPlayerXPosition());
        Assertions.assertEquals(8*300, humanPlayer.getPlayerYPosition());
    }

}
