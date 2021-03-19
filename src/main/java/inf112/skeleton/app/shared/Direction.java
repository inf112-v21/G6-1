package inf112.skeleton.app.shared;

public enum Direction {
    NORTH(0, 3900),
    EAST(90, 3900),
    SOUTH(180, 0),
    WEST(270, 0);

    private float direction;

    public float getDirection(){
        return this.direction;
    }

    private Direction(float direction, float boundaryCoordinate){
        this.direction = direction;
    }
}
