package coursework.tournamentbracketgenerator.validators;

import coursework.tournamentbracketgenerator.models.User;
import coursework.tournamentbracketgenerator.repositories.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateUserValidator")
public class UserValidator implements Validator {
    private final UserRepository repository;

    public UserValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(@NonNull Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(@NonNull Object o, @NonNull Errors errors) {
        User user = (User) o;

        if (user.getUsername() == null) {
            errors.rejectValue("username", "user.username.null");
        }
        if (repository.findByUsername(user.getUsername()).isPresent()) {
            errors.rejectValue("username", "user.username.unique");
        }

        if (user.getPassword() == null) {
            errors.rejectValue("password", "user.password.null");
        }
    }
}
