package Maven.Project.TicTacToe.controllers;


import Maven.Project.TicTacToe.exception.InvalidMoveException;
import Maven.Project.TicTacToe.exception.ResourceNotFoundException;
import Maven.Project.TicTacToe.model.Board;
import Maven.Project.TicTacToe.model.Game;
import Maven.Project.TicTacToe.model.Move;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/*
    This class interacts with Spring Boot through the use of
    Post and Get.
 */
@RestController
public class TicTacToeController {
    private static final String GREETING_MESSAGE = "Awesome Tic Tac Toe game written by <<Fred Wu>>\n!";
    private ArrayList<Game> games = new ArrayList<>();
    private AtomicLong nextId = new AtomicLong();

    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game createNewGame(@RequestBody Game game){
        game.setId(nextId.incrementAndGet());
        games.add(game);
        return game;
    }


    @GetMapping("/about")
    public String getAboutMessage(){
        return GREETING_MESSAGE;
    }

    @GetMapping("/games/{id}/moves")
    public List<Move> getAllMoves(@PathVariable("id") int gameId) throws ResourceNotFoundException {
        for(Game game : games)
        {
            if(game.getId() == gameId){
                return game.getMoves();
            }
        }
        throw new ResourceNotFoundException("Game for ID "+ gameId+ " not found.");
    }

    @GetMapping("/games")
    public List<Game> getAllGames(){
        return games;
    }

    @GetMapping("/games/{id}")
    public Game getOneGame(@PathVariable("id") long gameId) throws ResourceNotFoundException {
        for(Game game : games)
        {
            if(game.getId() == gameId){
                return game;
            }
        }

        throw new ResourceNotFoundException("Game for ID "+ gameId+ " not found.");
    }


    @GetMapping("/games/{id}/board")
    public Board getboard(@PathVariable("id") int gameId) throws ResourceNotFoundException {
        for(Game game : games)
        {
            if(game.getId() == gameId){
                return game.getBoard();
            }
        }
        throw new ResourceNotFoundException("Game for ID "+ gameId+ " not found.");
    }

    @PostMapping("/games/{id}/moves")
    @ResponseStatus(HttpStatus.CREATED)
    public Move makeMove(@PathVariable("id") int gameId,
                            @RequestBody Move newMove) throws InvalidMoveException, ResourceNotFoundException {
        for(Game game : games){
            if(game.getId() == gameId){
                return game.preCheck(newMove);
            }
        }

        throw new ResourceNotFoundException("Game for ID "+ gameId+ " not found.");
    }



    // Create Exception Handle
    @ResponseStatus(value = HttpStatus.NOT_FOUND,
            reason = "Requesting data for a game which does not exist!")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHandler() {
        // Nothing to do
    }

}


