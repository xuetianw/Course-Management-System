package Maven.Project.TicTacToe.model;

/*
    The board class stores different characters for
    each cell.
 */
public class Board {
    private StringBuilder row1;
    private StringBuilder row2;
    private StringBuilder row3;

    public Board(){
        row1 = new StringBuilder ("   ");
        row2 = new StringBuilder ("   ");
        row3 = new StringBuilder ("   ");
    }

    public StringBuilder getRow1() {
        if(row1.length() == 0 ) {
            row1 = new StringBuilder ("   ");
        }
        return row1;
    }

    public void setRow1(int col, String piece) {
        if (piece.equals("X")){
            row1.setCharAt(col, 'X');
        } else if (piece.equals("O")){
            row1.setCharAt(col, 'O');
        }
    }

    public StringBuilder getRow2() {
        if(row2.length() == 0 ) {
            row2 = new StringBuilder ("   ");
        }
        return row2;
    }

    public void setRow2(int col, String piece) {
        if (piece. equals ("X")){
            row2.setCharAt(col, 'X');
        } else if (piece.equals("O")){
            row2.setCharAt(col, 'O');
        }
    }

    public StringBuilder getRow3() {
        if(row3.length() == 0 ) {
            row3 = new StringBuilder ("   ");
        }
        return row3;
    }

    public void setRow3(int col, String piece) {
        if (piece.equals ("X")){
            row3.setCharAt(col, 'X');
        } else if (piece .equals("O")){
            row3.setCharAt(col, 'O');
        }
    }

    public String checkGameStatus() {

        if (this.getRow1().charAt(0) == 'X' && getRow1().charAt(1) == 'X'&&
                getRow1().charAt(2) == 'X') {
            return "X_WIN";
        } else if ((getRow2().charAt(0) == 'X' && getRow2().charAt(1) == 'X'&&
                getRow2().charAt(2) == 'X')) {
            return "X_WIN";
        } else if ((getRow3().charAt(0) == 'X' && getRow3().charAt(1) == 'X'&&
                getRow3().charAt(2) == 'X')) {
            return "X_WIN";
        } else if ((getRow1().charAt(0) == 'X' && getRow2().charAt(1) == 'X'&&
                getRow3().charAt(2) == 'X')) {
            return "X_WIN";
        } else if ((getRow1().charAt(2) == 'X' && getRow2().charAt(1) == 'X'&&
                getRow3().charAt(0) == 'X')) {
            return "X_WIN";
        } else if ((getRow1().charAt(0) == 'X' && getRow2().charAt(0) == 'X'&&
                getRow3().charAt(0) == 'X')) {
            return "X_WIN";
        } else if ((getRow1().charAt(1) == 'X' && getRow2().charAt(1) == 'X'&&
                getRow3().charAt(1) == 'X')) {
            return "X_WIN";
        } else if ((getRow1().charAt(2) == 'X' && getRow2().charAt(2) == 'X'&&
                getRow3().charAt(2) == 'X')) {
            return "X_WIN";
        } else if (getRow1().charAt(0) == 'O' && getRow1().charAt(1) == 'O'&&
                getRow1().charAt(2) == 'O') {
            return "O_WON";
        } else if ((getRow2().charAt(0) == 'O' && getRow2().charAt(1) == 'O'&&
                getRow2().charAt(2) == 'O')) {
            return "O_WON";
        } else if ((getRow3().charAt(0) == 'O' && getRow3().charAt(1) == 'O'&&
                getRow3().charAt(2) == 'O')) {
            return "O_WON";
        } else if ((getRow1().charAt(0) == 'O' && getRow2().charAt(1) == 'O'&&
                getRow3().charAt(2) == 'O')) {
            return "O_WON";
        } else if ((getRow1().charAt(2) == 'O' && getRow2().charAt(1) == 'O'&&
                getRow3().charAt(0) == 'O')) {
            return "O_WON";
        } else if ((getRow1().charAt(0) == 'O' && getRow2().charAt(0) == 'O'&&
                getRow3().charAt(0) == 'O')) {
            return "O_WON";
        } else if ((getRow1().charAt(1) == 'O' && getRow2().charAt(1) == 'O'&&
                getRow3().charAt(1) == 'O')) {
            return "O_WON";
        } else if ((getRow1().charAt(2) == 'O' && getRow2().charAt(2) == 'O'&&
                getRow3().charAt(2) == 'O')) {
            return "O_WON";
        }
        return null;
    }
}
