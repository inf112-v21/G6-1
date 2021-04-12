package inf112.skeleton.app.shared;

public enum Direction {
    NORTH(0, 3900, 1 ),
    EAST(90, 3300, 1),
    SOUTH(180, 0, -1),
    WEST(270, 0, -1);


    private final int directionDegree;
    private final int boundaryCoordinate;
    private final float moveDirection;

    public int getDirectionDegree(){
        return this.directionDegree;
    }

    public int getBoundaryCoordinate(){
        return this.boundaryCoordinate; }

    public float getMoveDirection(){
        return this.moveDirection;
    }


    Direction(int directionDegree, int boundaryCoordinate, int moveDirection){
        this.directionDegree = directionDegree;
        this.boundaryCoordinate = boundaryCoordinate;
        this.moveDirection = moveDirection;
    }
}
