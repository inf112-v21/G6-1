package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.graphics.Graphics;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("GameStopper's RoboRally");
        cfg.setWindowedMode(700, 700);
        Game game = new Game();

        new Lwjgl3Application(game.startGame(), cfg);
    }
}