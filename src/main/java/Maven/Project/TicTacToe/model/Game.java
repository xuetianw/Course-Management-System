package Maven.Project.TicTacToe.model;

import Maven.Project.TicTacToe.exception.InvalidMoveException;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;

/*
    Game class will help the TicTacToeController class
    to receive and send information to the Spring Boot
    server. It provides the TicTacToeController class
    with the necessary getters and setters that make
    communication possible.
 */
@JsonPropertyOrder({ "id", "description", "gameStatement", "moves", "board"  })
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

    public Move preCheck(Move newMove) throws InvalidMoveException {
        if (getMoves().size() == 0 && newMove.getPiece().equals("O")){
            throw new InvalidMoveException("X must make the first move.");
        } else if(newMove.getCol() < 0 || newMove.getRow() < 0 ||
                newMove.getCol()> 2 || newMove.getRow() > 2 ){
            throw new InvalidMoveException("Invalid move location (must be 0-2 for row and column)");
        } else if (!getGameState().equals("PLAYING")){
            throw new InvalidMoveException("Additional moves not allowed: game is over");
        } else if (getMoves().size() != 0 && getMoves()
                .get((getMoves().size())-1)
                .getPiece()
                .equals(newMove.getPiece())){
            throw new InvalidMoveException("Player may not play twice in a row.");
        } else if (!newMove.getPiece().equals("O")&&!newMove.getPiece().equals("X")){
            throw new InvalidMoveException("Invalid piece (must be X or O)");
        }

        int col = newMove.getCol();
        int row = newMove.getRow();
        Board board = getBoard();
        StringBuilder st = null;
        if (row == 0) {
            st = board.getRow1();
        } else if(row == 1) {
            st = board.getRow2();
        } else if(row == 2) {
            st = board.getRow3();
        }
        if (st != null && st.charAt(col) != ' ') {
            throw new InvalidMoveException("Invalid move location (duplicate of earlier move)");
        }
        newMove.setMoveNumber(getMoves().size() + 1);
        getMoves().add(newMove);
        if(newMove.getRow() == 0) {
            board.setRow1(newMove.getCol(),newMove.getPiece());
        } else if (newMove.getRow() == 1){
            board.setRow2(newMove.getCol(),newMove.getPiece());
        } else if (newMove.getRow() == 2){
            board.setRow3(newMove.getCol(),newMove.getPiece());
        }

        String feedback = board.checkGameStatus();
        if (feedback != null){
            if (feedback.equals("X_WIN")) {
                setGameStatement("X_WIN");
            } else if(feedback.equals("O_WON")) {
                setGameStatement("O_WON");
            }
        }
        return newMove;
    }
}
