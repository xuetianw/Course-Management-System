package io.futurestud.retrofit1.api.model;


import java.util.ArrayList;

/*
    Game class will help the TicTacToeController class
    to receive and send information to the Spring Boot
    server. It provides the TicTacToeController class
    with the necessary getters and setters that make
    communication possible.
 */
public class Game {
    private long id;
    private String description;
    private String gameState = "PLAYING";
    private ArrayList<Move> moves = new ArrayList<>();
    private Board board = new Board();

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameStatement(String gameState) {
        this.gameState = gameState;
    }

    public void setMoves(ArrayList<Move> moveList){
        this.moves = moveList;
    }

    public ArrayList<Move> getMoves(){
        return moves;
    }

}
