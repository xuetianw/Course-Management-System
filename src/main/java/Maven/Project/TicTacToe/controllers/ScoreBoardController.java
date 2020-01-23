package Maven.Project.TicTacToe.controllers;

import Maven.Project.TicTacToe.Service.UserService;
import Maven.Project.TicTacToe.exception.ResourceNotFoundException;
import Maven.Project.TicTacToe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@RestController
public class ScoreBoardController {

    private UserService userService;
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ScoreBoardController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/players")
    public List<User> getAllPlayers() {
        return userService.findAll();
    }

    @GetMapping("/players/{playerId}")
    public User findPlayer(@PathVariable int playerId) {

        User theUser = userService.findById(playerId);

        if (theUser != null) {
            return theUser;
        }

        throw new ResourceNotFoundException("player id does not existed - " + playerId);

    }

    @PostMapping("/players")
    @ResponseStatus(HttpStatus.CREATED)
    public User createNewPlayer(@RequestBody User user) {
        user.setId(0);

//        User thePlayer = userService.findById(user.getId());
//
//        if (thePlayer != null) {
//            throw new RuntimeException("user id existed - " + user.getEmail());
//        }

        userService.save(user);
        return user;
    }

    @PutMapping("/players")
    @ResponseStatus(HttpStatus.CREATED)
    public User updatePlayer(@RequestBody User user) {
//        User thePlayer = userService.findById(user.getId());
//
//        if (thePlayer == null) {
//            throw new RuntimeException("user email not found - " + user.getEmail());
//        }

        userService.save(user);
        return user;
    }

    @DeleteMapping("/players/{playerId}")
    public String deletePlayer(@PathVariable int playerId) {

        User theUser = userService.findById(playerId);

        // throw exception if null

        if (theUser == null) {
            throw new ResourceNotFoundException("player id not found - " + playerId);
        }

        userService.deleteById(playerId);

        return ("Deleted player id - " + playerId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public User addNewUser(@RequestBody User newUser) {


        // Clear its current ID (as we are going to give it one):
        // (Failing to do this, and having the ID 0 causes exceptions when we try to add it to repository)
//        newUser.setId(null);

        // Check it:
        if (newUser.getEmail() == null || newUser.getEmail().isEmpty()) {
            throw new ResourceNotFoundException("User email address must not be empty.");
        }
        if (newUser.getFirst_name() == null || newUser.getLast_name().isEmpty()) {
            throw new ResourceNotFoundException("User name must not be empty.");
        }
        if (newUser.getPassword() == null || newUser.getPassword().isEmpty()) {
            throw new ResourceNotFoundException("User password must not be empty.");
        }

        // Already exist?
        List<User> myUsers = userService.findAll();
        for (User user : myUsers) {
            if (user != null
                    && user.getEmail().equals(newUser.getEmail())
            ) {
                throw new InvalidLoginException("User with same email address already created.");
            }
        }

        // Secure the password:
//        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

        // Add it to repository, returning what is actually created in DB
        userService.save(newUser);
        return newUser;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/login")
    public User login(@RequestBody User theUser) {
        // Already exist?
        List<User> myUsers = userService.findAll();
        for (User user : myUsers) {
            if (user != null
                    && user.getEmail().equals(theUser.getEmail())
                    && user.getPassword().equals(theUser.getPassword())
            ) {
                return user;
            }
        }

        throw new InvalidLoginException("invalid credential.");
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public class InvalidLoginException extends RuntimeException {
        public InvalidLoginException(String s){
            super(s);
        }
    }


}
