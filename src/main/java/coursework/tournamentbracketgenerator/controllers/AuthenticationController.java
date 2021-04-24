package coursework.tournamentbracketgenerator.controllers;

import coursework.tournamentbracketgenerator.models.User;
import coursework.tournamentbracketgenerator.projections.JwtToken;
import coursework.tournamentbracketgenerator.repositories.UserRepository;
import coursework.tournamentbracketgenerator.services.UserService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
@RequestMapping("/api/")
public class AuthenticationController {
    private final UserRepository repository;
    private final UserService userService;

    public AuthenticationController(UserRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @GetMapping("auth")
    public ResponseEntity<JwtToken> auth(@RequestHeader(name = "Authorization", required = false, defaultValue = "") String authorization) {
        if (authorization.length() < 7) {
            throw new BadCredentialsException("Неккоректный формат токена");
        }
        return ResponseEntity.ok(new JwtToken(authorization.substring(7)));
    }

    @PostMapping("auth/signIn")
    public ResponseEntity<JwtToken> signIn(@RequestBody User user) {
        try {
            return ResponseEntity.ok(new JwtToken(userService.signIn(user)));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Неверный username или password");
        }
    }

    @PostMapping("auth/signUp")
    public ResponseEntity<JwtToken> signUp(@RequestBody User user) {
        if (user.getUsername() == null) {
            throw new BadCredentialsException("Параметр username является обязательным.");
        }
        if (repository.findByUsername(user.getUsername()).isPresent()) {
            throw new BadCredentialsException("Параметр username должен быть уникальным.");
        }

        if (user.getPassword() == null) {
            throw new BadCredentialsException("Параметр password является обязательным.");
        }

        return ResponseEntity.ok(new JwtToken(userService.register(user)));
    }
}
