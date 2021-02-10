package inf112.skeleton.app.player;

import inf112.skeleton.app.boardLogic.GameLogic;
import inf112.skeleton.app.location.Location;
import inf112.skeleton.app.shared.Action;
import inf112.skeleton.app.robot.Robot;

public class Player implements IPlayer{


    private final Robot robot; // private final Piece piece;
    private int lifeTokens;
    private int damageTokens;
    private final int id;
    private Location location;
    private Location checkPoint;

    @Override
    public Action[] getActions(BoardLogic board) {
        return new Action[0];
    }


    public Player(int id, Robot robot) {
        this.id = id;
        this.robot = robot;
        this.lifeTokens = 3;
        this.damageTokens = 0;
    }



    private void loseLifeToken() {
        this.lifeTokens -= 1;
    }

    public int getLifeTokens() { return lifeTokens; }

    /**
     * Return the players location
     * @return the current location;
     */
    public Location getLocation() {
        return location;
    }

}
