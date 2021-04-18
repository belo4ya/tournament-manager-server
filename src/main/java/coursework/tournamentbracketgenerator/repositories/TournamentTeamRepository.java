package coursework.tournamentbracketgenerator.repositories;

import coursework.tournamentbracketgenerator.models.TournamentTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentTeamRepository extends JpaRepository<TournamentTeam, Long> {
}
