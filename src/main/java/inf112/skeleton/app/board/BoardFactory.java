package inf112.skeleton.app.board;

public class BoardFactory {
    /**
     * Creates a simple 4x5 board with a starting point and a flag.
     * @return a simple 4x5 board
     */
    static IBoard simpleBoard() {
        IBoardPiece NORMAL = new BoardPiece(TileType.NORMAL);
        IBoardPiece FLAG = new BoardPiece(TileType.FLAG);
        IBoardPiece START_POINT = new BoardPiece(TileType.START_POINT);

        IBoardPiece[][] b = {
                {START_POINT, NORMAL, NORMAL, NORMAL},
                {NORMAL, NORMAL, NORMAL, NORMAL},
                {NORMAL, NORMAL, NORMAL, NORMAL},
                {NORMAL, NORMAL, NORMAL, NORMAL},
                {NORMAL, NORMAL, NORMAL, FLAG},
        };

        IBoard board = new Board(b);
        return board;
    }
}
