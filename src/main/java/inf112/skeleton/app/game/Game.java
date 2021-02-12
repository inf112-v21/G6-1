package inf112.skeleton.app.game;


import inf112.skeleton.app.GameLogic.GameLogic;
import inf112.skeleton.app.graphics.Graphics;

public class Game implements IGame {

        Graphics graphics;
        GameLogic gameLogic;

    @Override
    public Graphics startGame() {
        graphics = new Graphics();
        return graphics;
    }


}