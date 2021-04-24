package coursework.tournamentbracketgenerator.repositories;

import coursework.tournamentbracketgenerator.models.User;
import coursework.tournamentbracketgenerator.models.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    @RestResource(path = "by_username", rel = "by_username")
    Optional<User> findByUsername(String username);
}
