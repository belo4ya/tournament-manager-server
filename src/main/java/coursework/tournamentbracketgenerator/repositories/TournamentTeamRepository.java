package coursework.tournamentbracketgenerator.repositories;

import coursework.tournamentbracketgenerator.models.TournamentTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TournamentTeamRepository extends JpaRepository<TournamentTeam, Long> {
}
