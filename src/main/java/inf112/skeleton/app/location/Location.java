package inf112.skeleton.app.location;

public class Location implements ILocation {
    private final int x;
    private final int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getColumn() {
        return x;
    }

    @Override
    public int getRow() {
        return y;
    }
}
