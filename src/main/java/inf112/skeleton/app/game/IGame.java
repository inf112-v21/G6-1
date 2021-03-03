package inf112.skeleton.app.game;


import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.card.Card;
import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.player.HumanPlayer;

import java.util.ArrayList;
import java.util.HashMap;

public interface IGame {

    /**
     * Starts the game by utilizing graphics.
     * @return graphics
     */
    Graphics startGame();

    void executeMoves(HashMap<Integer, ArrayList<Card>> playerMoves);

    boolean isGameOver(TiledMapTileLayer flagLayer);

    void setUpGame ();

    ArrayList createPlayers();




}
