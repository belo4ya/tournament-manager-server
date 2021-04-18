package coursework.tournamentbracketgenerator.repositories;

import coursework.tournamentbracketgenerator.models.BracketNodeTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BracketNodeTeamRepository extends JpaRepository<BracketNodeTeam, Long> {
}
