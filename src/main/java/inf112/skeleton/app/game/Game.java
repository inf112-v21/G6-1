package inf112.skeleton.app.game;


import inf112.skeleton.app.graphics.Graphics;

public class Game implements IGame {
// TODO Game extends graphics and uses methods from player????
    private Graphics graphics;

    @Override
    public Graphics startGame() {
        graphics = new Graphics();
        return graphics;
    }




}