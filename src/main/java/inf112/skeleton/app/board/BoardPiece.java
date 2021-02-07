package inf112.skeleton.app.board;

import inf112.skeleton.app.shared.Direction;

public class BoardPiece implements IBoardPiece{

    private final TileType tileType;
    private final Direction direction;

    /**
     * Create a new board piece of a tile type facing north
     * @param tileType the tile type of the board piece
     */
    public BoardPiece(TileType tileType){
        this.tileType = tileType;
        this.direction = Direction.NORTH;
    }

    /**
     * Create a new board piece of a tile type with a direction
     * @param tileType the tile type of the board piece
     * @param direction the direction of the board piece
     */
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
