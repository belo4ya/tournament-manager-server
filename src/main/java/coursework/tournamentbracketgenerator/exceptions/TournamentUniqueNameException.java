package coursework.tournamentbracketgenerator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TournamentUniqueNameException extends RuntimeException{
    public TournamentUniqueNameException() {
        super("duplicate key value violates unique constraint Key (name)=(my tournament) already exists");
    }
}
