package coursework.tournamentbracketgenerator.services;

import coursework.tournamentbracketgenerator.models.Bracket;
import org.springframework.stereotype.Service;

@Service
public class SingleEliminationService {

    public Bracket generateBracket(Long tournamentId) {
        return new Bracket();
    }

    static public int countNumberOfTours(int numberOfTeams) {
        return (int) Math.ceil(Math.log(numberOfTeams) / Math.log(2));
    }

    static public int countNumberOfTeams(int numberOfTours) {
        return (int) Math.pow(2, numberOfTours);
    }

}
