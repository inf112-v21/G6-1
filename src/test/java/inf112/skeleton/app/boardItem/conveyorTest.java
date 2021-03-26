package inf112.skeleton.app.boardItem;

import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;
import inf112.skeleton.app.BoardItems.Conveyor;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


import org.junit.Before;


public class conveyorTest {

    HumanPlayer humanPlayer;
    Conveyor conveyor;
    final int tileSize = 300;


    @Before
    public void setup() {
        humanPlayer = new HumanPlayer(Direction.NORTH, 1, Color.GREEN);
        conveyor = new Conveyor();
    }

    @Test
    public void doesPlayerMoveOneTileOnYellowConveyor(){
        humanPlayer.setPlayerStartXPosition(4*tileSize);
        humanPlayer.setPlayerStartYPosition(3*tileSize);

        conveyor.movePlayerOnConveyor(humanPlayer, 0, 1*tileSize);

        Assertions.assertEquals(4*tileSize, humanPlayer.getPlayerXPosition());
        Assertions.assertEquals(4*tileSize, humanPlayer.getPlayerYPosition());
    }

    @Test
    public void doesPlayerMoveTwoTileOnBlueConveyor(){
        humanPlayer.setPlayerStartXPosition(6*tileSize);
        humanPlayer.setPlayerStartYPosition(5*tileSize);

        conveyor.movePlayerOnConveyor(humanPlayer, 180, 2*tileSize);

        Assertions.assertEquals(6*tileSize, humanPlayer.getPlayerXPosition());
        Assertions.assertEquals(3*tileSize, humanPlayer.getPlayerYPosition());
    }

    @Test
    public void doesPlayerMoveOneAtEndOfBlueConveyor(){
        humanPlayer.setPlayerStartXPosition(10*tileSize);
        humanPlayer.setPlayerStartYPosition(8*tileSize);

        conveyor.movePlayerOnConveyor(humanPlayer, 90, 2*tileSize);

        Assertions.assertEquals(11*tileSize, humanPlayer.getPlayerXPosition());
        Assertions.assertEquals(8*tileSize, humanPlayer.getPlayerYPosition());
    }

}
