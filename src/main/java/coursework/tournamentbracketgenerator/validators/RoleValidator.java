package coursework.tournamentbracketgenerator.validators;

import coursework.tournamentbracketgenerator.models.Role;
import coursework.tournamentbracketgenerator.repositories.RoleRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateRoleValidator")
public class RoleValidator implements Validator {
    private final RoleRepository repository;

    public RoleValidator(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(@NonNull Class<?> aClass) {
        return Role.class.equals(aClass);
    }

    @Override
    public void validate(@NonNull Object o, @NonNull Errors errors) {
        Role role = (Role) o;

        if (role.getName() == null) {
            errors.rejectValue("name", "name.null");
        }
        if (repository.findByName(role.getName()).isPresent()) {
            errors.rejectValue("name", "name.unique");
        }
    }
}
