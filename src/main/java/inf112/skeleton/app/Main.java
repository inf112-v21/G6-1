package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.graphics.Graphics;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("GameStopper's RoboRally");
        cfg.setWindowedMode(1280, 720);
        Game game = new Game();
        if(!game.players == null || !game.players.isEmpty()) {
            new Lwjgl3Application(game.startGame(), cfg);
        }

    }
}
