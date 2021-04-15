package inf112.skeleton.app.shared;

public enum Direction {
    NORTH(0, 3900, 1 ,'y'),
    EAST(90, 3300, 1, 'x'),
    SOUTH(180, 0, -1, 'y'),
    WEST(270, 0, -1,'x');


    private final int directionDegree;
    private final int boundaryCoordinate;
    private final float moveDirection;
    private final char xyDirection;

    public int getDirectionDegree(){
        return this.directionDegree;
    }

    public int getBoundaryCoordinate(){
        return this.boundaryCoordinate; }

    public float getMoveDirection(){
        return this.moveDirection;
    }
    public char getXyDirection(){
        return this.xyDirection;
    }


    Direction(int directionDegree, int boundaryCoordinate, int moveDirection, char xyDirection){
        this.directionDegree = directionDegree;
        this.boundaryCoordinate = boundaryCoordinate;
        this.moveDirection = moveDirection;
        this.xyDirection = xyDirection;
    }
}
