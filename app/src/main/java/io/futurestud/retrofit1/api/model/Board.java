package io.futurestud.retrofit1.api.model;

/*
    The board class stores different characters for
    each cell.
 */
public class Board {
    private StringBuilder row1 = new StringBuilder("   ");
    private StringBuilder row2 = new StringBuilder("   ");
    private StringBuilder row3 = new StringBuilder("   ");

    public Board(){

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


}
