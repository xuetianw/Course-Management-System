package Maven.Project.TicTacToe.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorisedException extends RuntimeException {
    public UnauthorisedException(String s) {
        super(s);
    }
}
