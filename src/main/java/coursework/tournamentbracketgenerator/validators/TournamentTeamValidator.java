package coursework.tournamentbracketgenerator.validators;

import coursework.tournamentbracketgenerator.models.TournamentTeam;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateTournamentTeamValidator")
public class TournamentTeamValidator implements Validator {

    @Override
    public boolean supports(@NonNull Class<?> aClass) {
        return TournamentTeam.class.equals(aClass);
    }

    @Override
    public void validate(@NonNull Object o, @NonNull Errors errors) {
        TournamentTeam tournamentTeam = (TournamentTeam) o;

        if (tournamentTeam.getTournament() == null) {
            errors.rejectValue("tournament", "tournament_team.tournament.null");
        }
        if (tournamentTeam.getTeam() == null) {
            errors.rejectValue("team", "tournament_team.team.null");
        }
    }
}
