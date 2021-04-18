package coursework.tournamentbracketgenerator.validators;

import coursework.tournamentbracketgenerator.models.BracketNode;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateBracketNodeValidator")
public class BracketNodeValidator implements Validator {
    @Override
    public boolean supports(@NonNull Class<?> aClass) {
        return BracketNode.class.equals(aClass);
    }

    @Override
    public void validate(@NonNull Object o, @NonNull Errors errors) {
        BracketNode bracketNode = (BracketNode) o;

        if (bracketNode.getBracket() == null) {
            errors.rejectValue("bracket", "bracketNode.bracket.null");
        }

        if (bracketNode.getRound() == null) {
            errors.rejectValue("round", "bracketNode.round.null");
        }
        if (bracketNode.getRound() < 0) {
            errors.rejectValue("round", "bracketNode.round.gte");
        }
    }
}
