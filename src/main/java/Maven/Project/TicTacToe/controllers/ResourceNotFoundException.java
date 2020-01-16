package Maven.Project.TicTacToe.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by wu on 2018-03-19.
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
    ResourceNotFoundException(String s) {
        super(s);
    }
}
