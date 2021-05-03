package coursework.tournamentbracketgenerator.controllers;

import coursework.tournamentbracketgenerator.repositories.BracketTypeRepository;
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


@RepositoryRestController
public class TournamentController {
    private final BracketTypeRepository bracketTypeRepository;
    private final BracketService bracketService;

    public TournamentController(BracketTypeRepository bracketTypeRepository, BracketService bracketService) {
        this.bracketTypeRepository = bracketTypeRepository;
        this.bracketService = bracketService;
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

        tournament.setBracketType(bracketTypeRepository.findById(tournament.getBracketType().getId()).orElseThrow());

        return new ResponseEntity<>(assembler.toModel(bracketService.createTournament(tournament)), HttpStatus.CREATED);
    }
}
