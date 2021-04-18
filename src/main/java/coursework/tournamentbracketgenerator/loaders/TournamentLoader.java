package coursework.tournamentbracketgenerator.loaders;

import coursework.tournamentbracketgenerator.models.Tournament;
import coursework.tournamentbracketgenerator.repositories.TournamentRepository;
import org.springframework.boot.CommandLineRunner;


//@Component
public class TournamentLoader implements CommandLineRunner {
    private final TournamentRepository tournamentRepository;

    public TournamentLoader(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public void run(String... args) {
        tournamentRepository.save(new Tournament());
    }
}
