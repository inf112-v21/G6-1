package inf112.skeleton.app.game;


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

    HumanPlayer createPlayers(int object);
}
