package coursework.tournamentbracketgenerator.repositories;

import coursework.tournamentbracketgenerator.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
