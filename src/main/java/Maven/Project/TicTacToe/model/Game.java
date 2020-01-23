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
@JsonPropertyOrder({"id", "description", "gameStatement", "moves", "board"})
public class Game {
    private long id;
    private String description;
    private String gameState = "PLAYING";
    private ArrayList<Move> moves = new ArrayList<>();

    private StringBuilder[] board;

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

    public void setMoves(ArrayList<Move> moveList) {
        this.moves = moveList;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void setCell(int row, int col, char piece) {
        board[row] = new StringBuilder().append(board[row].substring(0, col)).append(piece).append(board[row].substring(col + 1, 3));
    }

    public void setBoard(StringBuilder[] board) {
        this.board = board;
    }

    public StringBuilder[] getBoard() {
        return board;
    }

    public Move preCheck(Move newMove) {
        if (moves.size() == 0 && newMove.getPiece() == 'O') {
            throw new InvalidMoveException("X must make the first move.");
        } else if (newMove.getCol() < 0 || newMove.getRow() < 0 ||
                newMove.getCol() > 2 || newMove.getRow() > 2) {
            throw new InvalidMoveException("Invalid move location (must be 0-2 for row and column)");
        } else if (!getGameState().equals("PLAYING")) {
            throw new InvalidMoveException("Additional moves not allowed: game is over");
        } else if (moves.size() != 0 && moves
                .get((moves.size()) - 1)
                .getPiece()
                == newMove.getPiece()) {
            throw new InvalidMoveException("Player may not play twice in a row.");
        } else if (!(newMove.getPiece() == 'O' || newMove.getPiece() == 'X')) {
            throw new InvalidMoveException("Invalid piece (must be X or O)");
        }

        if (board[newMove.getRow()].charAt(newMove.getCol()) != ' ') {
            throw new InvalidMoveException("Invalid move location (duplicate of earlier move)");
        }

        newMove.setMoveNumber(moves.size() + 1);
        moves.add(newMove);
        setCell(newMove.getRow() , newMove.getCol(), newMove.getPiece());

        gameState = checkGameStatus();

        return newMove;
    }

    public String checkGameStatus() {
        String row1 = board[0].toString();
        String row2 = board[1].toString();
        String row3 = board[2].toString();
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
