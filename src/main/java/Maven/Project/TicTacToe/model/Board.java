package Maven.Project.TicTacToe.model;

/*
    The board class stores different characters for
    each cell.
 */
public class Board {
    private StringBuilder[] rows;

    public Board(){
        rows = new StringBuilder[3];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new StringBuilder("   ");
        }
    }

    public void setRows(StringBuilder[] rows) {
        this.rows = rows;
    }

    public StringBuilder[] getRows() {
        return rows;
    }


    public void setBoard(int row, int col, char piece) {
        rows[row] = new StringBuilder().append(rows[row].substring(0, col)).append(piece).append(rows[row].substring(col + 1, 3));
    }

    public String checkGameStatus() {
        String row1 = rows[0].toString();
        String row2 = rows[1].toString();
        String row3 = rows[2].toString();
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
