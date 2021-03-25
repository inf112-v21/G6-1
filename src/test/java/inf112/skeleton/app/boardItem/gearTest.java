package inf112.skeleton.app.boardItem;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.app.BoardItems.Conveyor;
import inf112.skeleton.app.BoardItems.Gear;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.Before;
import org.junit.Test;


public class gearTest {

    Lwjgl3ApplicationConfiguration cfg;
    Graphics graphics;
    HumanPlayer humanPlayer;
    Gear gear;


    @Before
    public void setup() {
        graphics = new Graphics(new Game());
        cfg = new Lwjgl3ApplicationConfiguration();
        humanPlayer = new HumanPlayer(Direction.NORTH, 1, Color.GREEN);
        gear = new Gear();
        new Lwjgl3Application(graphics, cfg);
    }
    @Test
    public void playerRotatesLeftWhenStoodOnRedGear(){
        humanPlayer.setPlayerStartXPosition(3*300);
        humanPlayer.setPlayerStartYPosition(5*300);
        humanPlayer.setPlayerDirection(0);

        gear.findAndRunGear(humanPlayer, );

        Assertions.assertEquals(Direction.WEST, humanPlayer.direction);
    }

}
