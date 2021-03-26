package inf112.skeleton.app.player;

import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class playerLogicTest {

    final HumanPlayer humanPlayer = new HumanPlayer(Direction.NORTH, 1, Color.GREEN);

    @Test
    public void testPlayersPositionMustBeOnGameBoard() {
        float xPositiveDirection = 3301;
        float xNegativeDirection = -1;
        float yPositiveDirection = 3901;
        float yNegativeDirection = -1;

        Assertions.assertFalse(humanPlayer.keepPlayerOnBoard(xPositiveDirection, 0));
        Assertions.assertFalse(humanPlayer.keepPlayerOnBoard(xNegativeDirection, 0));
        Assertions.assertFalse(humanPlayer.keepPlayerOnBoard(0, yPositiveDirection));
        Assertions.assertFalse(humanPlayer.keepPlayerOnBoard(0, yNegativeDirection));
    }

}
