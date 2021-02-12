package inf112.skeleton.app.game;


import inf112.skeleton.app.graphics.Graphics;

public class Game implements IGame {

    private Graphics graphics;

    @Override
    public Graphics startGame() {
        graphics = new Graphics();
        return graphics;
    }


}