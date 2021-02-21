package inf112.skeleton.app.shared;

public enum Action {
    MOVEONE(300),
    MOVETWO(600),
    MOVETHREE(900),
    ROTATE_LEFT(90),
    ROTATE_RIGHT(270),
    U_TURN(180);

    private int action;

    public int getAction(){
        return this.action;
    }
    private Action(int action){
        this.action = action;
    }

    //TODO should MVP actions just be move north, south, west, east
    //  or should we implement it with rotations?
}
