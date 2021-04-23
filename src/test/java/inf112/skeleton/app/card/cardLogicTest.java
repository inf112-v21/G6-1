package inf112.skeleton.app.card;

import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;

public class cardLogicTest {

    final HumanPlayer humanPlayer = new HumanPlayer(Direction.NORTH, 1, Color.GREEN);
    //TODO need to replace updatePlayerLocation with doPlayerMove(Card card, TileLayers tileLayers)
/**
    @Test
    public void testMoveOneCardMovesRobotOneSlot(){
        humanPlayer.setPlayerStartXPosition(0);
        humanPlayer.setPlayerStartYPosition(0);
        humanPlayer.direction = Direction.NORTH;
        MoveOne moveOneCard = new MoveOne(490, Action.MOVE_ONE);
        humanPlayer.updatePlayerLocation(moveOneCard);
        Assertions.assertEquals(300, humanPlayer.getPlayerYPosition());
        Assertions.assertEquals(humanPlayer.getPlayerXPosition(), 0);
    }

    @Test
    public void testMoveTwoCardMovesRobotTwoSlots(){
        humanPlayer.setPlayerStartXPosition(0);
        humanPlayer.setPlayerStartYPosition(0);
        humanPlayer.direction = Direction.NORTH;
        MoveTwo moveTwoCard = new MoveTwo(670, Action.MOVE_TWO);
        humanPlayer.updatePlayerLocation(moveTwoCard);
        Assertions.assertEquals(600, humanPlayer.getPlayerYPosition());
        Assertions.assertEquals(humanPlayer.getPlayerXPosition(), 0);
    }

    @Test
    public void testMoveThreeCardMovesRobotThreeSlots(){
        humanPlayer.setPlayerStartXPosition(0);
        humanPlayer.setPlayerStartYPosition(0);
        humanPlayer.direction = Direction.NORTH;
        MoveThree moveThreeCard = new MoveThree(790, Action.MOVE_THREE);
        humanPlayer.updatePlayerLocation(moveThreeCard);
        Assertions.assertEquals(900, humanPlayer.getPlayerYPosition());
        Assertions.assertEquals(humanPlayer.getPlayerXPosition(), 0);
    }

    @Test
    public void testRotateRightCardRotatesRobotToTheRight(){
        humanPlayer.setPlayerStartXPosition(0);
        humanPlayer.setPlayerStartYPosition(0);
        humanPlayer.direction = Direction.NORTH;
        RotateRight rotateRightCard = new RotateRight(80, Action.ROTATE_RIGHT);
        humanPlayer.updatePlayerLocation(rotateRightCard);
        Assertions.assertEquals(humanPlayer.direction, Direction.EAST);
    }

    @Test
    public void testRotateLeftCardRotatesRobotToTheLeft(){
        humanPlayer.setPlayerStartXPosition(0);
        humanPlayer.setPlayerStartYPosition(0);
        humanPlayer.direction = Direction.NORTH;
        RotateLeft rotateLeftCard = new RotateLeft(70, Action.ROTATE_LEFT);
        humanPlayer.updatePlayerLocation(rotateLeftCard);
        Assertions.assertEquals(humanPlayer.direction, Direction.WEST);
    }

    @Test
    public void testRotateCardDoesNotMoveRobot(){
        humanPlayer.setPlayerStartXPosition(0);
        humanPlayer.setPlayerStartYPosition(0);
        humanPlayer.direction = Direction.NORTH;
        RotateRight rotateRightCard = new RotateRight(80, Action.ROTATE_RIGHT);
        humanPlayer.updatePlayerLocation(rotateRightCard);
        Assertions.assertEquals(0, humanPlayer.getPlayerYPosition());
        Assertions.assertEquals(humanPlayer.getPlayerXPosition(), 0);
    }

    @Test
    public void testBackUpCardMovesRobotOneSlotBack(){
        humanPlayer.setPlayerStartXPosition(300);
        humanPlayer.setPlayerStartYPosition(300);
        humanPlayer.direction = Direction.NORTH;
        BackUp backUpCard = new BackUp(430, Action.BACK_UP);
        humanPlayer.updatePlayerLocation(backUpCard);
        Assertions.assertEquals(0, humanPlayer.getPlayerYPosition());
        Assertions.assertEquals(humanPlayer.getPlayerXPosition(), 300);
    }

    @Test
    public void testUTurnCardMovesRobotInUTurn(){
        humanPlayer.setPlayerStartXPosition(0);
        humanPlayer.setPlayerStartYPosition(0);
        humanPlayer.direction = Direction.NORTH;
        UTurn uTurnCard = new UTurn(10, Action.U_TURN);
        humanPlayer.updatePlayerLocation(uTurnCard);
        Assertions.assertEquals(humanPlayer.direction, Direction.SOUTH);
    }
    */
}
