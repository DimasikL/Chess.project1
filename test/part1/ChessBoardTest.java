package part1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {

    @Test
    void testPlacePiece() {
        ChessBoard board = new ChessBoard();
        King king = new King(0, 0, true);
        board.placePiece(king, 0, 0);
        assertSame(king, board.getPiece(0, 0));
    }

    @Test
    void testMovePiece() {
        ChessBoard board = new ChessBoard();
        King king = new King(0, 0, true);
        board.placePiece(king, 0, 0);
        board.movePiece(0, 0, 1, 1);
        assertSame(king, board.getPiece(1, 1));
        assertNull(board.getPiece(0, 0));
    }

    @Test
    void testClearSquare() {
        ChessBoard board = new ChessBoard();
        King king = new King(0, 0, true);
        board.placePiece(king, 0, 0);
        board.clearSquare(0, 0);
        assertNull(board.getPiece(0, 0));
    }
    @Test
    public void testIsValid() {
        ChessBoard board = new ChessBoard();
        assertFalse(board.isValid());

        board.placePiece(new King(0, 0, true), 0, 0);
        assertFalse(board.isValid());

        board.placePiece(new King(7, 7, false), 7, 7);
        assertTrue(board.isValid());

        board.placePiece(new Pawn(1, 1, true), 1, 1);
        assertTrue(board.isValid());

        for (int i = 0; i < 8; i++) {
            board.placePiece(new Pawn(2, i, true), 2, i);
        }
        assertFalse(board.isValid());

        board.clearSquare(1, 1);
        assertTrue(board.isValid());

        board.movePiece(0, 0, 6, 6);
        assertFalse(board.isValid());

        board.movePiece(6, 6, 3,3);
        assertTrue(board.isValid());

        board.placePiece(new King(4, 1, false), 4, 1);
        assertFalse(board.isValid());

    }

}