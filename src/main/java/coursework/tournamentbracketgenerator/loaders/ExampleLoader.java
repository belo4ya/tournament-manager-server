package coursework.tournamentbracketgenerator.loaders;

import coursework.tournamentbracketgenerator.models.Team;
import coursework.tournamentbracketgenerator.models.User;
import coursework.tournamentbracketgenerator.repositories.TeamRepository;
import coursework.tournamentbracketgenerator.repositories.TournamentRepository;
import coursework.tournamentbracketgenerator.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class ExampleLoader implements CommandLineRunner {
    private final TournamentRepository tournamentRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final User user;

    public ExampleLoader(TournamentRepository tournamentRepository, TeamRepository teamRepository, UserRepository userRepository) {
        this.tournamentRepository = tournamentRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.user = userRepository.findByUsername("user").orElseThrow();
    }

    @Override
    public void run(String... args) throws Exception {

    }

    public void createTeams() {
        String[] NAMES = {
                "Gambit", "Heroic", "Astralis", "Virtus Pro", "Natus Vincere", "BIG", "FURIA", "Liquid", "G2", "Complexity",
                "Spirit", "Vitality", "NIP", "HAVU", "Extra Salt", "FunPlus Phoenix", "mousesports", "FaZe", "OG", "fnatic",
                "EPG Family", "Evil Geniuses", "SKADE", "forZe", "Dignitas", "ENCE", "paiN", "AGO", "SAW", "Sprout"
        };
        String IMG_SDN_LOGO = "https://img-cdn.hltv.org/teamlogo/";
        String[] LOGOS = {
                /* 1 */ "pNV-lVdpvYZIkDwHdEXXg-.svg?ixlib=java-2.1.0&s=8b557b5b4d283208976340ef1bc44c76",
                /* 2 */ "Q6BGM_VQRz8Y9m3UgaqiUx.svg?ixlib=java-2.1.0&s=9424e99a6d77c8f154ea2d74175b3e17",
                /* 3 */ "bgXHp-oh1oaXr7F0mTGmd.svg?ixlib=java-2.1.0&s=f567161ab183001be33948b98c4b2067",
                /* 4 */ "yZ6Bpuui1rW3jocXQ68XgZ.svg?ixlib=java-2.1.0&s=f39be1d3e7baf30a4e7f0b1216720875",
                /* 5 */ "kixzGZIb9IYAAv-1vGrGev.svg?ixlib=java-2.1.0&s=8f9986a391fcb1adfbfff021b824a937",
                /* 6 */ "OgMRQA35hopXA8kDwMFHIY.svg?ixlib=java-2.1.0&s=ec7bc44165c7acf4224a22a1338ab7d7",
                /* 7 */ "OgMRQA35hopXA8kDwMFHIY.svg?ixlib=java-2.1.0&s=ec7bc44165c7acf4224a22a1338ab7d7",
                /* 8 */ "JMeLLbWKCIEJrmfPaqOz4O.svg?ixlib=java-2.1.0&s=c02caf90234d3a3ebac074c84ba1ea62",
                /* 9 */ "zFLwAELOD15BjJSDMMNBWQ.png?ixlib=java-2.1.0&w=50&s=affb583e6716d8ee904826992255cc4b",
                /* 10*/ "R0CzydpyX02BnkAYhy3I89.svg?ixlib=java-2.1.0&s=8c5833d6069ef924fdbb2e220fefea00",
                /* 11*/ "RbtyvoP_5E-pU-MWu9zy2V.svg?ixlib=java-2.1.0&s=ac4c6b3e927d5cb8a6c3158c871fa846",
                /* 12*/ "GAlByJtDTnkgbb9p_71SUL.png?ixlib=java-2.1.0&w=50&s=2838cd78a5ebb5c9fea4c485908e9dbb",
                /* 13*/ "-ttGATBV_P_HcZazxNNtIb.png?ixlib=java-2.1.0&w=50&s=ba94f7812d1f47183a83f3f34ab959eb",
                /* 14*/ "bGYa-QzoimP8JH5hcdXcu3.svg?ixlib=java-2.1.0&s=70ceee130e1571052313c6f74c078aa3",
                /* 15*/ "bGYa-QzoimP8JH5hcdXcu3.svg?ixlib=java-2.1.0&s=70ceee130e1571052313c6f74c078aa3",
                /* 16*/ "Min7KyMY3tuB2xt-nPyM-O.svg?ixlib=java-2.1.0&s=b037f336aeb9b89b9b09d4a4671f9b61",
                /* 17*/ "1YWxVoOcvOf3R9Z0-HWyeU.svg?ixlib=java-2.1.0&s=07f948624704c960960b962a9c0416c3",
                /* 18*/ "1YWxVoOcvOf3R9Z0-HWyeU.svg?ixlib=java-2.1.0&s=07f948624704c960960b962a9c0416c3",
                /* 19*/ "1YWxVoOcvOf3R9Z0-HWyeU.svg?ixlib=java-2.1.0&s=07f948624704c960960b962a9c0416c3",
                /* 20*/ "1YWxVoOcvOf3R9Z0-HWyeU.svg?ixlib=java-2.1.0&s=07f948624704c960960b962a9c0416c3",
                /* 21*/ "https://www.hltv.org/img/static/team/placeholder.svg",
                /* 22*/ "p6OXTVsTFEhOcbTJ8gmuP6.png?ixlib=java-2.1.0&w=50&s=41a619052f610fec335d17028d1f32af",
                /* 23*/ "YFPiz1uP4VOVNeqWOiFaOk.svg?ixlib=java-2.1.0&s=c62371c6760322616399d6975b845c87",
                /* 24*/ "Qnpb1nBNLJUCyf4fRMFbzr.svg?ixlib=java-2.1.0&s=a798b973c429361844ee174e07ae2401",
                /* 25*/ "w2EJ4p_IcYGMu0Cs2G4ZUn.png?ixlib=java-2.1.0&w=50&s=747bd21df637f066a412aaad3a30e4c0",
                /* 26*/ "-X8NoyWC_1gYqUHvZqcpkc.svg?ixlib=java-2.1.0&s=85bb9daa6f846fa097c5942f2565fdb8",
                /* 27*/ "iUUCFwCOFmOrwhB8q8smMg.svg?ixlib=java-2.1.0&s=1446e1cf3d02deb8190fe6efd14e4ce4",
                /* 28*/ "iUUCFwCOFmOrwhB8q8smMg.svg?ixlib=java-2.1.0&s=1446e1cf3d02deb8190fe6efd14e4ce4",
                /* 29*/ "iUUCFwCOFmOrwhB8q8smMg.svg?ixlib=java-2.1.0&s=1446e1cf3d02deb8190fe6efd14e4ce4",
                /* 30*/ "Ut8iEF66VCIbZGniMYn5jL.svg?ixlib=java-2.1.0&s=e6ae4904320fcbb566d098641e22a474",
        };
        teamRepository.save(new Team(
                "Gambit",
                "https://img-cdn.hltv.org/teamlogo/pNV-lVdpvYZIkDwHdEXXg-.svg?ixlib=java-2.1.0&s=8b557b5b4d283208976340ef1bc44c76",
                993,
                user
        ));
    }
}
