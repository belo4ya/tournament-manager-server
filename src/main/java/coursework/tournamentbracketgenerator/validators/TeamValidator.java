package coursework.tournamentbracketgenerator.validators;

import coursework.tournamentbracketgenerator.models.Team;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateTeamValidator")
public class TeamValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Team.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Team team = (Team) o;
        if (team.getRating() < 0) {
            errors.rejectValue("rating", "rating.empty");
        }
    }
}
