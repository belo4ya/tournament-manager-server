package coursework.tournamentbracketgenerator.repositories;

import coursework.tournamentbracketgenerator.models.Bracket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BracketRepository extends JpaRepository<Bracket, Long> {
}
