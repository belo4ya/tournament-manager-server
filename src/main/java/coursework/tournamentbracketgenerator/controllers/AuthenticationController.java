package coursework.tournamentbracketgenerator.controllers;

import coursework.tournamentbracketgenerator.exceptions.JwtAuthenticationException;
import coursework.tournamentbracketgenerator.models.User;
import coursework.tournamentbracketgenerator.projections.JwtToken;
import coursework.tournamentbracketgenerator.repositories.UserRepository;
import coursework.tournamentbracketgenerator.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class AuthenticationController {
    private final UserService userService;
    private final UserRepository userRepository;

    public AuthenticationController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("auth")
    public ResponseEntity<JwtToken> auth(@RequestHeader(name = "Authorization", required = false, defaultValue = "Bearer  ") String authorization) {
        if (authorization.length() < 8) {
            throw new AccessDeniedException("ds");
        }
        return ResponseEntity.ok(new JwtToken(authorization.substring(7)));
    }

    @PostMapping("auth/signin")
    public ResponseEntity<JwtToken> signIn(@RequestBody User user) {
        try {
            return ResponseEntity.ok(new JwtToken(userService.signIn(user)));
        } catch (JwtAuthenticationException e) {
            throw new AccessDeniedException(e.getMessage());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("auth/signup")
    public ResponseEntity<JwtToken> register(@Validated @RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).orElse(null) != null) {
            throw new BadCredentialsException("Username must be unique");
        }
        if (user.getUsername() == null || user.getPassword() == null) {
            throw new BadCredentialsException("Username and password are required");
        }

        try {
            return ResponseEntity.ok(new JwtToken(userService.register(user)));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

}
