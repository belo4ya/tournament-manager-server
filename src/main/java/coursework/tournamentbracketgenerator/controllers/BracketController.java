package coursework.tournamentbracketgenerator.controllers;

import coursework.tournamentbracketgenerator.models.Bracket;
import coursework.tournamentbracketgenerator.services.SingleEliminationService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestController
public class BracketController {
    private final SingleEliminationService singleEliminationService;

    public BracketController(SingleEliminationService singleEliminationService) {
        this.singleEliminationService = singleEliminationService;
    }

    @GetMapping("/brackets/generate")
    public Bracket generateBracket(@RequestParam(name = "tournament_id") Long tournamentId) {
        System.out.println(tournamentId);
        return singleEliminationService.generateBracket(tournamentId);
    }
}
