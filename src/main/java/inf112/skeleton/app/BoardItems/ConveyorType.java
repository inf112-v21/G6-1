package inf112.skeleton.app.BoardItems;

public enum ConveyorType {
    COMMON(300),
    EXPRESS(600);

    private int numberOfMoves;

    public int getNumberOfMoves(){
        return this.numberOfMoves;
    }

    ConveyorType(int numberOfMoves){
        this.numberOfMoves = numberOfMoves;
    }
}

