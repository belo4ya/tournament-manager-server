package coursework.tournamentbracketgenerator.handlers;

import coursework.tournamentbracketgenerator.models.Team;
import coursework.tournamentbracketgenerator.models.Tournament;
import coursework.tournamentbracketgenerator.models.User;
import coursework.tournamentbracketgenerator.repositories.UserRepository;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class AuthorizedUserEventHandler {
    private final UserRepository repository;

    public AuthorizedUserEventHandler(UserRepository repository) {
        this.repository = repository;
    }

    @HandleBeforeCreate
    @HandleBeforeSave
    public void applyUserContextToTeam(Team team) {
        team.setUser(getAuthorizedUser());
    }

    @HandleBeforeCreate
    @HandleBeforeSave
    public void applyUserContextToTournament(Tournament tournament) {
        tournament.setUser(getAuthorizedUser());
    }

    private User getAuthorizedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return repository.findByUsername(username).orElse(null);
    }
}
