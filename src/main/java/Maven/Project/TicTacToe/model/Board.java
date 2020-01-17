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


    public StringBuilder getRow2() {
        return row2;
    }



    public StringBuilder getRow3() {
        return row3;
    }


    public void setBoard(int row, int col, char piece) {
        if (row == 0) {
            row1.setCharAt(col, piece);
        } else if (row == 1) {
            row2.setCharAt(col, piece);
        } else if (row == 2) {
            row3.setCharAt(col, piece);
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
        return "PLAYING";
    }
}
