package coursework.tournamentbracketgenerator.services;

import coursework.tournamentbracketgenerator.models.Role;
import coursework.tournamentbracketgenerator.models.User;
import coursework.tournamentbracketgenerator.repositories.RoleRepository;
import coursework.tournamentbracketgenerator.repositories.UserRepository;
import coursework.tournamentbracketgenerator.security.jwt.JwtTokenProvider;
import org.hibernate.mapping.Collection;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public String register(User user) {
        List<Role> roles = user.getRoles();
        if (roles != null) {
            roles = roles.stream()
                    .map(role -> roleRepository.findByName(role.getName()).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } else {
            roles = Collections.singletonList(roleRepository.findByName("ROLE_USER").orElseThrow());
        }

        user.setRoles(roles);
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        User registeredUser = userRepository.save(user);
        return jwtTokenProvider.createToken(registeredUser.getUsername(), registeredUser.getRoles());
    }

    public String signIn(User user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        User authorizedUser = findByUsername(user.getUsername());
        return jwtTokenProvider.createToken(authorizedUser.getUsername(), authorizedUser.getRoles());
    }

    public User findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
