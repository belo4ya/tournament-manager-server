package coursework.tournamentbracketgenerator.validators;

import coursework.tournamentbracketgenerator.models.Bracket;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateBracketValidator")
public class BracketValidator implements Validator {
    @Override
    public boolean supports(@NonNull Class<?> aClass) {
        return Bracket.class.equals(aClass);
    }

    @Override
    public void validate(@NonNull Object o, @NonNull Errors errors) {
        Bracket bracket = (Bracket) o;

        if (bracket.getTournament() == null) {
            errors.rejectValue("tournament", "bracket.tournament.null");
        }
        if (bracket.getType() == null) {
            errors.rejectValue("type", "bracket.type.null");
        }
    }
}
