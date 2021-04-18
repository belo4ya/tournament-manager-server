package coursework.tournamentbracketgenerator.validators;

import coursework.tournamentbracketgenerator.models.Tournament;
import coursework.tournamentbracketgenerator.repositories.TournamentRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateTournamentValidator")
public class TournamentValidator implements Validator {
    private final TournamentRepository repository;

    public TournamentValidator(TournamentRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(@NonNull Class<?> aClass) {
        return Tournament.class.equals(aClass);
    }

    @Override
    public void validate(@NonNull Object o, @NonNull Errors errors) {
        Tournament tournament = (Tournament) o;

        if (tournament.getName() == null) {
            errors.rejectValue("name", "name.null");
        }
        if (repository.findByName(tournament.getName()).isPresent()) {
            errors.rejectValue("name", "name.unique");
        }
    }
}
