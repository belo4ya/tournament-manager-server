package coursework.tournamentbracketgenerator.validators;

import coursework.tournamentbracketgenerator.models.Team;
import coursework.tournamentbracketgenerator.repositories.TeamRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateTeamValidator")
public class TeamValidator implements Validator {
    private final TeamRepository repository;

    public TeamValidator(TeamRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(@NonNull Class<?> aClass) {
        return Team.class.equals(aClass);
    }

    @Override
    public void validate(@NonNull Object o, @NonNull Errors errors) {
        Team team = (Team) o;

        if (team.getName() == null) {
            errors.rejectValue("name", "team.name.null");
        }
        if (!repository.findByNameIgnoreCase(team.getName()).isEmpty()) {
            errors.rejectValue("name", "team.name.unique");
        }

        if (team.getRating() == null || team.getRating() < 0) {
            errors.rejectValue("rating", "team.rating");
        }
    }
}
