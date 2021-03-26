package inf112.skeleton.app.boardItem;


import inf112.skeleton.app.BoardItems.Hole;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class holeTest {
    HumanPlayer humanPlayer;
    Hole hole;
    final ArrayList<Player> testPlayers = new ArrayList<>();
    final int tileSize = 300;

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
