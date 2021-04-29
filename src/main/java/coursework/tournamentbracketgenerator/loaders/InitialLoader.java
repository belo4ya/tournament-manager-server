package coursework.tournamentbracketgenerator.loaders;

import coursework.tournamentbracketgenerator.models.BracketType;
import coursework.tournamentbracketgenerator.models.Role;
import coursework.tournamentbracketgenerator.models.User;
import coursework.tournamentbracketgenerator.repositories.BracketTypeRepository;
import coursework.tournamentbracketgenerator.repositories.RoleRepository;
import coursework.tournamentbracketgenerator.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;


//@Component
@Slf4j
public class InitialLoader implements CommandLineRunner {
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_USER = "ROLE_USER";
    private static final String[] BRACKET_TYPES = {"Single Elimination", "Double Elimination", "Round-Robin"};
    private final RoleRepository roleRepository;
    private final UserService userService;
    private final BracketTypeRepository bracketTypeRepository;
    private Role userRole;
    private Role adminRole;

    public InitialLoader(RoleRepository roleRepository, UserService userService, BracketTypeRepository bracketTypeRepository) {
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.bracketTypeRepository = bracketTypeRepository;
    }

    @Override
    public void run(String... args) {
        createRoles();
        createUsers();
        createBracketTypes();
    }

    private void createRoles() {
        adminRole = roleRepository.findByName(ROLE_ADMIN).orElse(roleRepository.save(new Role(ROLE_ADMIN)));
        userRole = roleRepository.findByName(ROLE_USER).orElse(roleRepository.save(new Role(ROLE_USER)));
    }

    private void createUsers() {
        try {
            userService.register(new User("admin", "admin", Collections.singletonList(adminRole)));
            userService.register(new User("user", "user", Collections.singletonList(userRole)));
        } catch (Exception e) {
            log.warn("Пользователи admin и user не были созданы: {}", e, e);
        }

    }

    private void createBracketTypes() {
        Arrays.stream(BRACKET_TYPES).forEach((type) -> {
            bracketTypeRepository.findByType(type).orElse(bracketTypeRepository.save(new BracketType(type)));
        });
    }
}
