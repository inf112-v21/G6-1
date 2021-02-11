package inf112.skeleton.app.game;
import inf112.skeleton.app.GameLogic.GameLogic;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.player.Player;


public class Game implements IGame {
    GameLogic gameLogic = new GameLogic();
    Graphics graphics = new Graphics();

    @Override
    public void startGame() {
        graphics.create();
        gameLogic.setPlayerStartPosition(20,20);
        gameLogic.setPlayerSize(32,32);
        graphics.render();
        graphics.dispose();

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
