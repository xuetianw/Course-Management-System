package Maven.Project.TicTacToe.controllers;

import Maven.Project.TicTacToe.Service.PlayerService;
import Maven.Project.TicTacToe.model.Game;
import Maven.Project.TicTacToe.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScoreBoardController {

    private PlayerService playerService;

    @Autowired
    public ScoreBoardController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @GetMapping("/players")
    public List<Player> getAllPlayers(){
        return playerService.findAll();
    }

    @GetMapping("/player/{playerId}")
    public Player findPlayer(@PathVariable int playerId) {

        Player thePlayer = playerService.findById(playerId);

        if (thePlayer != null) {
            return thePlayer;
        }

        throw new RuntimeException("player id does not existed - " + playerId);

    }

    @PostMapping("/players")
    @ResponseStatus(HttpStatus.CREATED)
    public Player createNewPlayer(@RequestBody Player player) {
        player.setId(0);

//        Player thePlayer = playerService.findById(player.getId());
//
//        if (thePlayer != null) {
//            throw new RuntimeException("player id existed - " + player.getEmail());
//        }

        playerService.save(player);
        return player;
    }

    @PutMapping("/players")
    @ResponseStatus(HttpStatus.CREATED)
    public Player updatePlayer(@RequestBody Player player){
//        Player thePlayer = playerService.findById(player.getId());
//
//        if (thePlayer == null) {
//            throw new RuntimeException("player email not found - " + player.getEmail());
//        }

        playerService.save(player);
        return player;
    }

    @DeleteMapping("/player/{employeeId}")
    public String deleteEmployee(@PathVariable int playerId) {

        Player thePlayer = playerService.findById(playerId);

        // throw exception if null

        if (thePlayer == null) {
            throw new RuntimeException("player id not found - " + playerId);
        }

        playerService.deleteById(playerId);

        return ("Deleted player id - " + playerId);
    }
}
