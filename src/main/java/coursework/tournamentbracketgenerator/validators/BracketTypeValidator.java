package coursework.tournamentbracketgenerator.validators;

import coursework.tournamentbracketgenerator.models.BracketType;
import coursework.tournamentbracketgenerator.repositories.BracketTypeRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateBracketTypeValidator")
public class BracketTypeValidator implements Validator {
    private final BracketTypeRepository repository;

    public BracketTypeValidator(BracketTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(@NonNull Class<?> aClass) {
        return BracketType.class.equals(aClass);
    }

    @Override
    public void validate(@NonNull Object o, @NonNull Errors errors) {
        BracketType bracketType = (BracketType) o;

        if (bracketType.getType() == null) {
            errors.rejectValue("type", "bracket_type.type.null");
        }
        if (repository.findByType(bracketType.getType()).isPresent()) {
            errors.rejectValue("type", "bracket_type.type.unique");
        }
    }
}
