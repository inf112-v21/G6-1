package inf112.skeleton.app.player;

import inf112.skeleton.app.board.IBoard;
import inf112.skeleton.app.shared.Action;

public interface IPlayer {

    /**
     * Get a list of actions from the player
     * @return actions
     */
    Action[] getActions(IBoard board);

    //TODO future deliveries will have damage tokens, health, player name, player piece etc.
}
