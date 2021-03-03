package inf112.skeleton.app.game;


import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.player.HumanPlayer;

import java.util.ArrayList;

public interface IGame {

    /**
     * Starts the game by utilizing graphics.
     * @return graphics
     */
    Graphics startGame();

    void executeMoves();

    boolean isGameOver(TiledMapTileLayer flagLayer);

    void setUpGame ();

    ArrayList createPlayers();




}
