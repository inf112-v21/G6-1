package inf112.skeleton.app.shared;

public enum Direction {
    NORTH(0),
    EAST(90),
    SOUTH(180),
    WEST(270);

    private float direction;

    public float getDirection(){
        return this.direction;
    }

    private Direction(float direction){
        this.direction = direction;
    }
}
