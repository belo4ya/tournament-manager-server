package coursework.tournamentbracketgenerator.repositories;

import coursework.tournamentbracketgenerator.models.BracketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

public interface BracketTypeRepository extends JpaRepository<BracketType, Long> {

    @RestResource(path = "by-type", rel = "by-type")
    Optional<BracketType> findByType(String type);
}
