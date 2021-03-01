package inf112.skeleton.app.game;


import inf112.skeleton.app.graphics.Graphics;
import inf112.skeleton.app.player.HumanPlayer;

import java.util.ArrayList;

public interface IGame {

    /**
     * Starts the game by utilizing graphics.
     * @return graphics
     */
    Graphics startGame();

    void playGame ();

    void gameOver();

    void setUpGame ();

    ArrayList createPlayers(int numberOfPlayers);




}
