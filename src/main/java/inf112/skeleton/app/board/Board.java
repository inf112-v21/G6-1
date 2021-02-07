package inf112.skeleton.app.board;

import inf112.skeleton.app.location.Location;

public class Board implements IBoard {
    /** The width (number of columns) of the board */
    private final int width;
    /** Height (number of rows) of the board */
    private final int height;
    /** The board pieces */
    private final IBoardPiece[][] board;

    public Board(IBoardPiece[][] boardPieces) {
        // TODO handle possible exception if boardPieces is null or empty list
        this.board = boardPieces;
        this.width = boardPieces.length;
        this.height = boardPieces[0].length;
    }


    @Override
    public IBoardPiece[][] getBoardPieces() {
        return new IBoardPiece[0][];
    }

    @Override
    public int getBoardWidth() {
        return width;
    }

    @Override
    public int getBoardHeight() {
        return height;
    }

    @Override
    public IBoardPiece getBoardPiece(Location location) throws IndexOutOfBoundsException {
        return this.board[location.getColumn()][location.getRow()];
    }
}
