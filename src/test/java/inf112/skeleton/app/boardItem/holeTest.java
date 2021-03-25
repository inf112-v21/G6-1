package inf112.skeleton.app.boardItem;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.app.BoardItems.Conveyor;
import inf112.skeleton.app.BoardItems.Hole;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class holeTest {
    HumanPlayer humanPlayer;
    Hole hole;
    ArrayList<Player> testPlayers = new ArrayList<>();
    int tileSize = 300;

    @Before
    public void setup() {
        humanPlayer = new HumanPlayer(Direction.NORTH, 1, Color.GREEN);
        hole = new Hole();
        testPlayers.add(humanPlayer);
    }


    @Test
    public void doesPlayerTakeOneHealthAndReturnToStartIfInHole() {
        humanPlayer.setPlayerStartXPosition(2*tileSize);
        humanPlayer.setPlayerStartYPosition(12*tileSize);

        hole.playersInHole(testPlayers,2*tileSize, 12*tileSize );

        Assertions.assertEquals(2, humanPlayer.getPlayerHealth());
        Assertions.assertEquals(0, humanPlayer.getPlayerXPosition());
        Assertions.assertEquals(0, humanPlayer.getPlayerYPosition());
    }

}
