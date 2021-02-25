package inf112.skeleton.app.game;


import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.Client;
import inf112.skeleton.app.networking.GameClient;
import inf112.skeleton.app.networking.GameServer;

import inf112.skeleton.app.graphics.Graphics;



public class Game implements IGame {

    @Override
    public Graphics startGame() {
        Graphics graphics = new Graphics();
        return graphics;
    }


}