package inf112.skeleton.app.player;

import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.robot.Robot;

public class LocalHumanPlayer implements IPlayer{


    private final Robot robot; // private final Piece piece;
    private int lifeTokens;
    private int damageTokens;
    private final int id;



    @Override
    public Action[] getActions(IBoard board) {
        //TODO implement from keyboard clicks to rotate+movement
        return null;
    }

    public LocalHumanPlayer(int id, Robot robot) {
        this.id = id;
        this.robot = robot;
    }


    private void loseLifeToken() {
        this.lifeTokens -= 1;
    }
    


}
