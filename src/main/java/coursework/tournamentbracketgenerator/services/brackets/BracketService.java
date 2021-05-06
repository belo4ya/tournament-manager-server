package coursework.tournamentbracketgenerator.services.brackets;

import coursework.tournamentbracketgenerator.models.*;
import coursework.tournamentbracketgenerator.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BracketService {
    private final UserRepository userRepository;
    private final TournamentTeamRepository tournamentTeamRepository;
    private final TeamRepository teamRepository;
    private final BracketNodeTeamRepository bracketNodeTeamRepository;
    private final BracketRepository bracketRepository;
    private final BracketNodeRepository bracketNodeRepository;
    private final TournamentRepository tournamentRepository;
    private final BracketTypeRepository bracketTypeRepository;

    public BracketService(UserRepository userRepository, TournamentTeamRepository tournamentTeamRepository, TeamRepository teamRepository, BracketNodeTeamRepository bracketNodeTeamRepository, BracketRepository bracketRepository, BracketNodeRepository bracketNodeRepository, TournamentRepository tournamentRepository, BracketTypeRepository bracketTypeRepository) {
        this.userRepository = userRepository;
        this.tournamentTeamRepository = tournamentTeamRepository;
        this.teamRepository = teamRepository;
        this.bracketNodeTeamRepository = bracketNodeTeamRepository;
        this.bracketRepository = bracketRepository;
        this.bracketNodeRepository = bracketNodeRepository;
        this.tournamentRepository = tournamentRepository;
        this.bracketTypeRepository = bracketTypeRepository;
    }

    @Transactional
    public Tournament doMagic(TournamentDto dto) throws Exception {
        Tournament tournament = createTournament(dto);

        List<TournamentTeam> participants = createParticipants(dto, tournament);

        shuffle(SeedType.valueOf(dto.getSeedType().toUpperCase()), participants);

        String bracketType = dto.getBracketType().getType();
        //TODO: роутинг
        Bracket bracket = singleElimination(participants, tournament);

        tournament.setBrackets(Collections.singletonList(bracket));
        return tournament;
    }

    private Bracket singleElimination(List<TournamentTeam> participants, Tournament tournament) throws Exception {
        BracketType bracketType = bracketTypeRepository.findByType("Single Elimination").orElseThrow();
        Bracket bracket = createBracket(tournament, bracketType);

        List<BracketNode> bracketNodes = new ArrayList<>();
        for (int i = 0; i < participants.size(); i += 2) {
            System.out.println("i: " + i);
            BracketNode node = createBracketNode(0, bracket);

            List<BracketNodeTeam> opponents = new ArrayList<>();
            for (int j = 0; j < 2 && i + j < participants.size(); j++) {
                System.out.println("i + j: " + (i + j));
                BracketNodeTeam opponent = new BracketNodeTeam();
                opponent.setTournamentTeam(participants.get(i + j));
                opponent.setBracketNode(node);
                opponents.add(bracketNodeTeamRepository.save(opponent));
            }
            node.setTeams(opponents);
            bracketNodes.add(node);
        }

        int roundsNumber = countRounds(participants);
        int nominalParticipantsNumber = countNominalParticipants(roundsNumber);

        for (int i = 0; i < nominalParticipantsNumber - participants.size(); i++) {
            bracketNodes.add(createBracketNode(0, bracket));
        }

        System.out.println("Now: " + bracketNodes.size());
        int totalNodes = countBracketNodes(roundsNumber);
        System.out.println("Target: " + totalNodes);

        int round = 1;
        int prev = bracketNodes.size() / 2;
        while (bracketNodes.size() != totalNodes) {
            System.out.println("Round: " + round);
            for (int i = 0; i < prev; i++) {
                bracketNodes.add(createBracketNode(round, bracket));
            }
            round++;
            prev /= 2;
        }

        bracketNodes.forEach((node) -> System.out.println("Round: " + node.getRound()));

        bracket.setBracketNodes(bracketNodes);
        return bracketRepository.save(bracket);
    }

    private void doubleElimination(List<TournamentTeam> participants) {

    }

    private void roundRobin(List<TournamentTeam> participants) {

    }

    private Tournament createTournament(TournamentDto dto) {
        Tournament tournament = new Tournament();
        tournament.setName(dto.getName());
        tournament.setLogo(dto.getLogo());
        tournament.setUser(userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null));
        return tournamentRepository.save(tournament);
    }

    private List<TournamentTeam> createParticipants(TournamentDto dto, Tournament tournament) {
        return dto.getTeams().stream().map((t) -> {
            TournamentTeam tt = new TournamentTeam();
            tt.setTournament(tournament);
            tt.setTeam(teamRepository.findById(t.getId()).orElseThrow());
            return tournamentTeamRepository.save(tt);
        }).collect(Collectors.toList());
    }

    private Bracket createBracket(Tournament tournament, BracketType type) {
        Bracket bracket = new Bracket();
        bracket.setType(type);
        bracket.setTournament(tournament);
        return bracketRepository.save(bracket);
    }

    private BracketNode createBracketNode(Integer round, Bracket bracket) {
        BracketNode node = new BracketNode();
        node.setBracket(bracket);
        node.setRound(round);
        return bracketNodeRepository.save(node);
    }

    private void shuffle(SeedType seeding, List<TournamentTeam> participants) {
        if (seeding == SeedType.RANDOM) {
            Collections.shuffle(participants);
        } else if (seeding == SeedType.RATING) {
            participants.sort(Comparator.comparingInt(a -> a.getTeam().getRating()));
        }
    }

    private int countRounds(List<TournamentTeam> participants) {
        return (int) Math.ceil((Math.log(participants.size()) / Math.log(2)));
    }

    private int countNominalParticipants(int roundNumber) {
        return (int) Math.pow(2, roundNumber);
    }

    private int countBracketNodes(int roundsNumber) {
        return (int) (Math.pow(2, roundsNumber) - 1);
    }
}
