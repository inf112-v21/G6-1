package gameLogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.GameLogic.GameLogic;
import inf112.skeleton.app.game.Game;
import org.junit.Test;
import java.awt.event.KeyEvent;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameLogicTest {
    Sprite playerSprite;
    GameLogic gameLogic;


    public void setup() {
        Sprite playerSprite = new Sprite();
        GameLogic gameLogic = new GameLogic(playerSprite, new Game());


    }



    @Test
    public void testGameIsOverIfPlayerStartsOnFlag() throws Exception {
        int flagX = 1;
        int flagY = 9; //TODO find actual coordinates

        // TODO translate flag coord to pixel coords which are much larger
        //  i.e. if each tile is 32px then tile (1,9) becomes (32, 288)

        gameLogic.setPlayerStartPosition(flagX, flagY);
        assertTrue(gameLogic.isGameOver());
    }

    @Test
    public void testGameIsNotOverIfPlayerIsNotOnFlag() throws Exception {
        gameLogic.setPlayerStartPosition(0, 0);
        assertTrue(!gameLogic.isGameOver());
        ;
    }

    @Test
    public void testPlayersPositionMustBeOnGameBoard() throws Exception {

    }

    @Test
    public void testPlayerMovesUpWhenUpKeyIsPressed() throws Exception {
        //set player position
        gameLogic.setPosition(1,1);
        //simulate key press
        //check if player position has moved

    }

    @Test
    public void testPlayerMovesDownWhenUpDownIsPressed() throws Exception {

    }

    @Test
    public void testPlayerMovesRightWhenRightKeyIsPressed() throws Exception {

    }

    @Test
    public void testPlayerMovesLeftWhenLeftKeyIsPressed() throws Exception {

    }

}
