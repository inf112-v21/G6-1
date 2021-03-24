package inf112.skeleton.app.shared;

public enum Direction {
    NORTH(0, 3900),
    EAST(90, 3300),
    SOUTH(180, 0),
    WEST(270, 0);

    private int directionDegree;
    private int boundaryCoordinate;

    public int getDirectionDegree(){
        return this.directionDegree;
    }

    public int getBoundaryCoordinate(){
        return this.boundaryCoordinate; }


    Direction(int directionDegree, int boundaryCoordinate){
        this.directionDegree = directionDegree;
        this.boundaryCoordinate = boundaryCoordinate;
    }
}
