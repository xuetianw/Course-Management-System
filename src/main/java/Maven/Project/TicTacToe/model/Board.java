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
        return row1;
    }

    public void setRow1(int col, char piece) {
        if (piece == 'X'){
            row1.setCharAt(col, 'X');
        } else if (piece == 'O') {
            row1.setCharAt(col, 'O');
        }
    }

    public StringBuilder getRow2() {
        return row2;
    }

    public void setRow2(int col, char piece) {
        if (piece == 'X') {
            row2.setCharAt(col, 'X');
        } else if (piece == 'O') {
            row2.setCharAt(col, 'O');
        }
    }

    public StringBuilder getRow3() {
        return row3;
    }

    public void setRow3(int col, char piece) {
        if (piece == 'X') {
            row3.setCharAt(col, 'X');
        } else if (piece == 'O') {
            row3.setCharAt(col, 'O');
        }
    }

    public String checkGameStatus() {
        String row1 = this.row1.toString();
        String row2 = this.row2.toString();
        String row3 = this.row3.toString();

        String s  = "" + row1.charAt(0) + row2.charAt(1) + row3.charAt(2);
        String s1 = "" + row1.charAt(2) + row2.charAt(1) + row3.charAt(0);
        String s2 = "" + row1.charAt(0) + row2.charAt(0) + row3.charAt(0);
        String s3 = "" + row1.charAt(1) + row2.charAt(1) + row3.charAt(1);
        String s4 = "" + row1.charAt(2) + row2.charAt(2) + row3.charAt(2);
        if (row1.equals("XXX")  || row2.equals("XXX") || row3.equals("XXX")
                || s.equals("XXX")
                || s1.equals("XXX")
                || s2.equals("XXX")
                || s3.equals("XXX")
                || s4.equals("XXX"))
        {
            return "X_WIN";
        } else if (row1.equals("OOO")  || row2.equals("OOO") || row3.equals("OOO")
                || s.equals("OOO")
                || s1.equals("OOO")
                || s2.equals("OOO")
                || s3.equals("OOO")
                || s4.equals("OOO"))
        {
            return "O_WON";
        }
        return null;
    }
}
