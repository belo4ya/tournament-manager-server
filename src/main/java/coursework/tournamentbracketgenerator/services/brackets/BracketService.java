package coursework.tournamentbracketgenerator.services.brackets;

import coursework.tournamentbracketgenerator.models.Tournament;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BracketService {

    static public Tournament createTournament(TournamentDto dto) {

        Tournament tournament = new Tournament();
//        tournament.setBrackets();

        tournament.setName(dto.getName());
        tournament.setLogo(dto.getLogo());
//        tournament.setTeams(dto.getTeams());
        return tournament;
    }
}
