package inf112.skeleton.app.game;
import inf112.skeleton.app.GameLogic.GameLogic;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.player.Player;

import inf112.skeleton.app.GameLogic.GameLogic;

public class Game implements IGame {
    //GameLogic gameLogic = new GameLogic();
    Graphics graphics;

    @Override
    public Graphics startGame() {
        graphics = new Graphics();
        return graphics;
    }

    @Override
    public void executeActionOn() {

    }

    @Override
    public boolean isGameOver() {

        /*
        if (flagIsVisited) {
            return true;
        } else {
            return false;
        }

         */
        return false;
    }
}
