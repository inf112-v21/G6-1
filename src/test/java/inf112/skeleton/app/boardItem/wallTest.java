package inf112.skeleton.app.boardItem;

import inf112.skeleton.app.BoardItems.Walls;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class wallTest {
    HumanPlayer humanPlayer;
    Walls walls;
    final int tileSize = 300;
    final ArrayList<Player> testPlayers = new ArrayList<>();

    @Before
    public void setUp() {
        humanPlayer = new HumanPlayer(Direction.NORTH, 1, Color.GREEN);
        walls = new Walls();
        testPlayers.add(humanPlayer);
    }

    @Test
    public void playerDoesNotMoveIfHitWall() {
        humanPlayer.setPlayerStartXPosition(tileSize);
        humanPlayer.setPlayerStartYPosition(2*tileSize);



        Assert.assertTrue(walls.hasPlayerAndWallSameLocation(
                humanPlayer, humanPlayer.getPlayerXPosition(), humanPlayer.getPlayerYPosition()));

    }

}
