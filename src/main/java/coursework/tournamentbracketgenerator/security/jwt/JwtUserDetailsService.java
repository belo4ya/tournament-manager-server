package coursework.tournamentbracketgenerator.security.jwt;

import coursework.tournamentbracketgenerator.models.User;
import coursework.tournamentbracketgenerator.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow();
        return new JwtUserDetails(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(
                        role -> new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList())
        );
    }
}
