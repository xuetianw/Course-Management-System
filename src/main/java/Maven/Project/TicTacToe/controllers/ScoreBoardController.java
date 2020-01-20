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

    @GetMapping("/player")
    public Player findPlayer(@RequestBody Player player) {

        Player thePlayer = playerService.findById(player.getEmail());

        if (thePlayer != null) {
            return player;
        }

        throw new RuntimeException("player email does not existed - ");

    }

    @PostMapping("/player")
    @ResponseStatus(HttpStatus.CREATED)
    public Player createNewPlayer(@RequestBody Player player) {

        Player thePlayer = playerService.findById(player.getEmail());

        if (thePlayer != null) {
            throw new RuntimeException("player email existed - " + player.getEmail());
        }

        playerService.save(player);
        return player;
    }

    @PutMapping("/player")
    @ResponseStatus(HttpStatus.CREATED)
    public Player updatePlayer(@RequestBody Player player){
        Player thePlayer = playerService.findById(player.getEmail());

        if (thePlayer == null) {
            throw new RuntimeException("player email not found - " + player.getEmail());
        }

        playerService.save(player);
        return player;
    }

    @DeleteMapping("/player")
    public String deleteEmployee(@RequestBody Player player) {

        Player thePlayer = playerService.findById(player.getEmail());

        // throw exception if null

        if (thePlayer == null) {
            throw new RuntimeException("player email not found - " + player.getEmail());
        }

        playerService.deleteById(player.getEmail());

        return "Deleted player email - " + player.getEmail();
    }
}
