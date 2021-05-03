package coursework.tournamentbracketgenerator.validators;

import coursework.tournamentbracketgenerator.models.BracketNodeTeam;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateBracketNodeTeamValidator")
public class BracketNodeTeamValidator implements Validator {
    @Override
    public boolean supports(@NonNull Class<?> aClass) {
        return BracketNodeTeam.class.equals(aClass);
    }

    @Override
    public void validate(@NonNull Object o, @NonNull Errors errors) {
        BracketNodeTeam bracketNodeTeam = (BracketNodeTeam) o;

        if (bracketNodeTeam.getBracketNode() == null) {
            errors.rejectValue("bracketNode", "bracketNode_team.bracketNode.null");
        }
        if (bracketNodeTeam.getTournamentTeam() == null) {
            errors.rejectValue("team", "bracketNode_team.team.null");
        }
    }
}
