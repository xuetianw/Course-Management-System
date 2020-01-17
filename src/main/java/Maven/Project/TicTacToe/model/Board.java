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
        String row1 = getRow1().toString();
        String row2 = getRow2().toString();
        String row3 = getRow3().toString();

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
