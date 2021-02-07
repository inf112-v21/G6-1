package inf112.skeleton.app.board;

import inf112.skeleton.app.shared.Direction;

public class BoardPiece implements IBoardPiece{

    private final TileType tileType;
    private final Direction direction;

    public BoardPiece(TileType tileType, Direction direction){
        this.tileType = tileType;
        this.direction = direction;
    }

    @Override
    public TileType getTileType() {
        return tileType;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }
}
