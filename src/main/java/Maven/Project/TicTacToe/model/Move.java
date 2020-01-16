package Maven.Project.TicTacToe.model;

/*
    Move class is responsible for receiving and setting the
    information about each move taken by the user.
 */
public class Move {
    private int moveNumber = 0;
    private String piece;
    private int row;
    private int col;

    public Move() {
    }

    public Move(int moveNumber, String piece, int row, int col) {
        this.moveNumber = moveNumber;
        this.piece = piece;
        this.row = row;
        this.col = col;
    }

    public int getMoveNumber() {
        return moveNumber;
    }

    public void setMoveNumber(int moveNumber) {
        this.moveNumber = moveNumber;
    }

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
