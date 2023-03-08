package part1;
public class ChessBoard {
    private static final int row = 8;
    private static final int col = 8;
    private Piece[][] board;
    private King whiteKing;
    private King blackKing;
    private int numWhitePawns;
    private int numBlackPawns;

    public ChessBoard() {
        board = new Piece[row][col];
        numWhitePawns = 0;
        numBlackPawns = 0;
    }

    public boolean isValid() {
        if (whiteKing == null || blackKing == null) {
            return false;
        }

        int whiteKingRow = whiteKing.getRow();
        int whiteKingCol = whiteKing.getCol();
        int blackKingRow = blackKing.getRow();
        int blackKingCol = blackKing.getCol();

        if (Math.abs(whiteKingRow - blackKingRow) <= 1 && Math.abs(whiteKingCol - blackKingCol) <= 1) {
            return false;
        }

        int numWhiteKings = 0;
        int numBlackKings = 0;
        numWhitePawns = 0;
        numBlackPawns = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Piece piece = board[i][j];
                if (piece instanceof King) {
                    if (piece.isWhite()) {
                        numWhiteKings++;
                    } else {
                        numBlackKings++;
                    }
                } else if (piece instanceof Pawn) {
                    if (piece.isWhite()) {
                        numWhitePawns++;
                    } else {
                        numBlackPawns++;
                    }
                }
            }
        }

        return numWhiteKings == 1 && numBlackKings == 1 && numWhitePawns <= 8 && numBlackPawns <= 8;
    }

    public void clearSquare(int row, int col) {
        Piece piece = board[row][col];
        if (piece instanceof King) {
            if (piece.isWhite()) {
                whiteKing = null;
            } else {
                blackKing = null;
            }
        } else if (piece instanceof Pawn) {
            if (piece.isWhite()) {
                numWhitePawns--;
            } else {
                numBlackPawns--;
            }
        }
        board[row][col] = null;
    }

    public void placePiece(Piece piece, int row, int col) {
        if (piece instanceof King) {
            if (piece.isWhite()) {
                whiteKing = (King) piece;
            } else {
                blackKing = (King) piece;
            }
        } else if (piece instanceof Pawn) {
            if (piece.isWhite()) {
                numWhitePawns++;
            } else {
                numBlackPawns++;
            }
        }
        board[row][col] = piece;
    }

    public void movePiece(int oldRow, int oldCol, int newRow, int newCol) {
        Piece piece = board[oldRow][oldCol];
        board[oldRow][oldCol] = null;
        board[newRow][newCol] = piece;
        piece.move(newRow, newCol);
    }
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }
}

abstract class Piece {
    private int row;
    private int col;
    private boolean isWhite;

    public Piece(int row, int col, boolean isWhite) {
        this.row = row;
        this.col = col;
        this.isWhite = isWhite;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void move(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
    }
}

class King extends Piece {
    public King(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }
}
class Pawn extends Piece {
    public Pawn(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }
}
