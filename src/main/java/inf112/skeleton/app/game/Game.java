package inf112.skeleton.app.game;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.networking.GameClient;
import inf112.skeleton.app.networking.GameServer;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Direction;
import inf112.skeleton.app.networking.*;

import java.net.InetAddress;
import java.util.ArrayList;

public class Game implements IGame, InputProcessor {

    private Graphics graphics;
    int numberOfPlayers;
    GameServer server;
    GameClient client;




    @Override
    public Graphics startGame() {
        graphics = new Graphics();
        server = new GameServer("RiskyExchange");
        server.run();
        client = new GameClient();
        return graphics;
    }

    //TODO Launche serveren i Game; forslag til metode til det:
    public InetAddress hostNewGame(String map) {
        server = new server(map);
        server.run();

        client = new GameClient(server.getAddress());
        return server.getAddress();
    }

    public void InetAddress joinNewGame(InetAddress ip) {
        client = new client(ip);

    }


    @Override
    public void playGame() {
        startGame();
        //this.numberOfPlayers = 8; //TODO make this function

        // do rounds
        // cardsToBePlayed ();
        // stop condition pga render
    }


    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public void setUpGame() {
        // ask number of players
        // await num of player selection

        // present board choices
        // await board selection

    }

    @Override
    public ArrayList<Player> createPlayers(int numberOfPlayers) {
        ArrayList <Player> playerList = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            playerList.add(new HumanPlayer(Direction.NORTH, "Vilde", "erlend"));
        }
        return playerList;

    }

    @Override
    public boolean keyDown(int i) {
        if (i == Input.Keys.NUM_1) {
            numberOfPlayers = 1;
        } else if (i == Input.Keys.NUM_2) {
            numberOfPlayers = 2;
        } else if (i == Input.Keys.NUM_3) {
            numberOfPlayers = 3;
        } else if (i == Input.Keys.NUM_4) {
            numberOfPlayers = 4;
        } else if (i == Input.Keys.NUM_5) {
            numberOfPlayers = 5;
        } else if (i == Input.Keys.NUM_6) {
            numberOfPlayers = 6;
        } else if (i == Input.Keys.NUM_7) {
            numberOfPlayers = 7;
        } else if (i == Input.Keys.NUM_8) {
            numberOfPlayers = 8;
        }
        return true;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}