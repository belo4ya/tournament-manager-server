package coursework.tournamentbracketgenerator.repositories;

import coursework.tournamentbracketgenerator.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @RestResource(path = "by-name", rel = "by-name")
    Optional<Role> findByName(String name);
}
