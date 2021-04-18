package coursework.tournamentbracketgenerator.repositories;

import coursework.tournamentbracketgenerator.models.BracketNode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BracketNodeRepository extends JpaRepository<BracketNode, Long> {
}
