package inf112.skeleton.app.board;

import inf112.skeleton.app.shared.Direction;

public interface IBoardPiece {
    /**
     * Gets the tile type of the board piece.
     * @return TileType
     */
    TileType getTileType();

    /**
     * Gets the direction of the board piece.
     * Some pieces are directional such as a conveyor belt and lasers.
     * @return Direction
     */
    Direction getDirection();

}
