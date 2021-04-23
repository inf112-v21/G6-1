package inf112.skeleton.app.boardItem;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.app.BoardItems.Gear;
import inf112.skeleton.app.BoardItems.Walls;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.card.MoveOne;
import inf112.skeleton.app.card.MoveThree;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;
import java.util.ArrayList;

public class wallTest {

    Lwjgl3ApplicationConfiguration cfg;
    Graphics graphics;
    Player humanPlayer;
    final float tileSize = 300;
    Card cardMoveOne = new MoveOne(300, Action.MOVE_ONE);
    Card cardMoveThree = new MoveThree(300, Action.MOVE_THREE);

    @Before
    public void setup() {
        graphics = new Graphics(new Game());
        cfg = new Lwjgl3ApplicationConfiguration();
        humanPlayer = new HumanPlayer(Direction.NORTH, 1, Color.GREEN);
        new Lwjgl3Application(graphics, cfg);
    }


    @Test
    public void playerDoesNotMoveIfHitWallOnNextTile() {
        humanPlayer.setPlayerStartXPosition(2*tileSize);
        humanPlayer.setPlayerStartYPosition(tileSize);
        humanPlayer.doPlayerMove(cardMoveOne,graphics.layer);
        Assertions.assertEquals(humanPlayer.getPlayerYPosition() ,tileSize);
        Assertions.assertEquals(humanPlayer.getPlayerXPosition() , 2*tileSize);

    }


    @Test
    public void playerDoesNotMoveIfHitWallOnSameTile() {
        humanPlayer.setPlayerStartXPosition(2*tileSize);
        humanPlayer.setPlayerStartYPosition(2*tileSize);
        humanPlayer.direction = Direction.SOUTH;
        humanPlayer.doPlayerMove(cardMoveOne,graphics.layer);
        Assertions.assertEquals(humanPlayer.getPlayerYPosition() ,2*tileSize);
        Assertions.assertEquals(humanPlayer.getPlayerXPosition() , 2*tileSize);

    }


    @Test
    public void playerMovesUpToWallAndStopsInFrontOfTheWall() {
        humanPlayer.setPlayerStartXPosition(2*tileSize);
        humanPlayer.setPlayerStartYPosition(3*tileSize);
        humanPlayer.direction = Direction.SOUTH;
        humanPlayer.doPlayerMove(cardMoveThree,graphics.layer);
        Assertions.assertEquals(humanPlayer.getPlayerYPosition() ,2*tileSize);
        Assertions.assertEquals(humanPlayer.getPlayerXPosition() , 2*tileSize);

    }

    @Test
    public void playerTakesDamageWhenHittingAWall(){
        humanPlayer.setPlayerStartXPosition(2*tileSize);
        humanPlayer.setPlayerStartYPosition(tileSize);
        humanPlayer.doPlayerMove(cardMoveOne,graphics.layer);
        Assertions.assertEquals(humanPlayer.damageTaken ,1);

    }

}
