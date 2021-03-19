package inf112.skeleton.app.shared;

public enum Direction {
    NORTH(0, 3900),
    EAST(90, 3900),
    SOUTH(180, 0),
    WEST(270, 0);

    private float direction;
    private int boundaryCoordinate;

    public float getDirection(){
        return this.direction;
    }

    public int getBoundaryCoordinate(){return this.boundaryCoordinate; }


    private Direction(float direction, int boundaryCoordinate){
        this.direction = direction;
    }
}
