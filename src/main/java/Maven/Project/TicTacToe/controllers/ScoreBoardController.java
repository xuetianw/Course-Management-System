package Maven.Project.TicTacToe.controllers;

import Maven.Project.TicTacToe.Service.PlayerService;
import Maven.Project.TicTacToe.model.Game;
import Maven.Project.TicTacToe.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@RestController
public class ScoreBoardController {

    private PlayerService playerService;
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public Player addNewUser(@RequestBody Player newUser) {


        // Clear its current ID (as we are going to give it one):
        // (Failing to do this, and having the ID 0 causes exceptions when we try to add it to repository)
//        newUser.setId(null);

        // Check it:
        if (newUser.getEmail() == null || newUser.getEmail().isEmpty()) {
            throw new RuntimeException("User email address must not be empty.");
        }
        if (newUser.getFirst_name() == null || newUser.getLast_name().isEmpty()) {
            throw new RuntimeException("User name must not be empty.");
        }
        if (newUser.getPassword() == null || newUser.getPassword().isEmpty()) {
            throw new RuntimeException("User password must not be empty.");
        }

        // Already exist?
        List<Player> myUsers = playerService.findAll();
        for (Player user : myUsers) {
            if (user != null
                    && user.getEmail().equalsIgnoreCase(newUser.getEmail())
            ) {
                throw new RuntimeException("User with same email address already created.");
            }
        }

        // Secure the password:
//        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

        // Add it to repository, returning what is actually created in DB
        playerService.save(newUser);
        return newUser;
    }
}
