package coursework.tournamentbracketgenerator.controllers;

import coursework.tournamentbracketgenerator.repositories.BracketTypeRepository;
import coursework.tournamentbracketgenerator.repositories.TeamRepository;
import coursework.tournamentbracketgenerator.repositories.TournamentRepository;
import coursework.tournamentbracketgenerator.services.brackets.BracketService;
import coursework.tournamentbracketgenerator.services.brackets.SeedType;
import coursework.tournamentbracketgenerator.services.brackets.TournamentDto;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.stream.Collectors;


@RepositoryRestController
public class TournamentController {
    private final TournamentRepository tournamentRepository;
    private final BracketTypeRepository bracketTypeRepository;
    private final TeamRepository teamRepository;

    public TournamentController(TournamentRepository tournamentRepository, BracketTypeRepository bracketTypeRepository, TeamRepository teamRepository) {
        this.tournamentRepository = tournamentRepository;
        this.bracketTypeRepository = bracketTypeRepository;
        this.teamRepository = teamRepository;
    }

    @PostMapping("/tournaments/withBracket")
    public ResponseEntity<PersistentEntityResource> createTournament(@RequestBody TournamentDto tournament, PersistentEntityResourceAssembler assembler) {
        if (tournament.getName().equals("") || tournament.getName() == null || tournament.getBracketType() == null ||
                tournament.getSeedType() == null || tournament.getTeams() == null) {
            throw new ResourceNotFoundException();
        }

        SeedType seedType = SeedType.valueOf(tournament.getSeedType().toUpperCase());
        if (!(seedType == SeedType.FIFO || seedType == SeedType.RANDOM || seedType == SeedType.RATING)) {
            throw new ResourceNotFoundException();
        }

        tournament.setBracketType(bracketTypeRepository.findById(tournament.getBracketType().getId()).orElseThrow(ResourceNotFoundException::new));
        tournament.setTeams(tournament.getTeams().stream().map((t) -> teamRepository.findById(t.getId()).orElseThrow(ResourceNotFoundException::new)).collect(Collectors.toList()));

        return new ResponseEntity<>(assembler.toModel(tournamentRepository.save(BracketService.createTournament(tournament))), HttpStatus.CREATED);
    }
}
