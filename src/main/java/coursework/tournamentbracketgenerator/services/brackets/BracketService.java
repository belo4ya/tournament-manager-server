package coursework.tournamentbracketgenerator.services.brackets;

import coursework.tournamentbracketgenerator.models.*;
import coursework.tournamentbracketgenerator.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BracketService {
    private final TournamentTeamRepository tournamentTeamRepository;
    private final TeamRepository teamRepository;
    private final BracketNodeTeamRepository bracketNodeTeamRepository;
    private final BracketRepository bracketRepository;
    private final BracketNodeRepository bracketNodeRepository;
    private final TournamentRepository tournamentRepository;

    public BracketService(TournamentTeamRepository tournamentTeamRepository, TeamRepository teamRepository, BracketNodeTeamRepository bracketNodeTeamRepository, BracketRepository bracketRepository, BracketNodeRepository bracketNodeRepository, TournamentRepository tournamentRepository) {
        this.tournamentTeamRepository = tournamentTeamRepository;
        this.teamRepository = teamRepository;
        this.bracketNodeTeamRepository = bracketNodeTeamRepository;
        this.bracketRepository = bracketRepository;
        this.bracketNodeRepository = bracketNodeRepository;
        this.tournamentRepository = tournamentRepository;
    }

    @Transactional
    public Tournament createTournament(TournamentDto dto) {
        Tournament tournament = new Tournament();
        tournament.setName(dto.getName());
        tournament.setLogo(dto.getLogo());
        tournament = tournamentRepository.save(tournament);

        Tournament finalTournament = tournament;
        List<TournamentTeam> tournamentTeams = dto.getTeams().stream().map((t) -> {
            TournamentTeam tt = new TournamentTeam();
            tt.setTournament(finalTournament);
            tt.setTeam(teamRepository.findById(t.getId()).orElseThrow());
            return tournamentTeamRepository.save(tt);
        }).collect(Collectors.toList());

        Bracket bracket = new Bracket();
        bracket.setTournament(tournament);
        bracket.setType(dto.getBracketType());
        bracket = bracketRepository.save(bracket);

        List<BracketNode> bracketNodes = new ArrayList<>();
        for (int i = 0; i < tournamentTeams.size(); i += 2) {
            BracketNode node = new BracketNode();
            node.setBracket(bracket);
            node.setRound(0);
            node = bracketNodeRepository.save(node);

            List<BracketNodeTeam> bracketNodeTeams = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                BracketNodeTeam bracketNodeTeam = new BracketNodeTeam();
                bracketNodeTeam.setTournamentTeam(tournamentTeams.get(i + j));
                bracketNodeTeam.setBracketNode(node);
                bracketNodeTeams.add(bracketNodeTeamRepository.save(bracketNodeTeam));
            }

            node.setTeams(bracketNodeTeams);
            bracketNodes.add(node);
        }

        bracket.setBracketNodes(bracketNodes);
        bracket = bracketRepository.save(bracket);

        tournament.setBrackets(Collections.singletonList(bracket));
        return tournament;
    }
}
