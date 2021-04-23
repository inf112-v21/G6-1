package inf112.skeleton.app.player;

import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class PlayerLogicTest {

    final HumanPlayer humanPlayer = new HumanPlayer(Direction.NORTH, 1, Color.GREEN);

    @Test
    public void testPlayersPositionMustBeOnGameBoard() {
        float xPositiveDirection = 3301;
        float xNegativeDirection = -1;
        float yPositiveDirection = 3901;
        float yNegativeDirection = -1;

        Assertions.assertFalse(humanPlayer.isPlayerOnBoard(xPositiveDirection, 0));
        Assertions.assertFalse(humanPlayer.isPlayerOnBoard(xNegativeDirection, 0));
        Assertions.assertFalse(humanPlayer.isPlayerOnBoard(0, yPositiveDirection));
        Assertions.assertFalse(humanPlayer.isPlayerOnBoard(0, yNegativeDirection));
    }

}
