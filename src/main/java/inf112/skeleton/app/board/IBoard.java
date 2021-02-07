package inf112.skeleton.app.board;

import inf112.skeleton.app.location.Location;

public interface IBoard {
    /**
     * Gets the board pieces as a two dimensional array
     * @return array of the board pieces
     */
    IBoardPiece[][] getBoardPieces();

    /**
     * Gets the board width
     * @return int board width
     */
    int getBoardWidth();

    /**
     * Gets the board height
     * @return int board height
     */
    int getBoardHeight();

    /**
     * Gives the board piece at a given location
     * @param location - The location we want to get the board piece of
     * @return board piece from the location
     * @throws IndexOutOfBoundsException - If the location is not on game board
     */
    IBoardPiece getBoardPiece(Location location) throws IndexOutOfBoundsException;

}
