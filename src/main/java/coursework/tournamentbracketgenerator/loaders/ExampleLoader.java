package coursework.tournamentbracketgenerator.loaders;

import coursework.tournamentbracketgenerator.models.Team;
import coursework.tournamentbracketgenerator.models.Tournament;
import coursework.tournamentbracketgenerator.models.User;
import coursework.tournamentbracketgenerator.repositories.TeamRepository;
import coursework.tournamentbracketgenerator.repositories.TournamentRepository;
import coursework.tournamentbracketgenerator.repositories.UserRepository;
import coursework.tournamentbracketgenerator.security.jwt.JwtTokenProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


//@Component
public class ExampleLoader implements CommandLineRunner {
    private final TournamentRepository tournamentRepository;
    private final TeamRepository teamRepository;
    private final User user;

    public ExampleLoader(TournamentRepository tournamentRepository, TeamRepository teamRepository, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTYyMDAyNzM2NiwiZXhwIjoxNjIwMDMwOTY2fQ.ocbPdZtzm_EqeDNtOJPov7Do2BIYv_3UmdFhsWyBp84";
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        this.tournamentRepository = tournamentRepository;
        this.teamRepository = teamRepository;
        this.user = userRepository.findByUsername("user").orElseThrow();
    }

    @Override
    public void run(String... args) throws Exception {
        createTeams();
        createTournaments();
    }

    public void createTeams() {
        String[] NAMES = {
                "Gambit", "Heroic", "Astralis", "Virtus Pro", "Natus Vincere", "BIG", "FURIA", "Liquid", "G2", "Complexity",
                "Spirit", "Vitality", "NIP", "HAVU", "Extra Salt", "FunPlus Phoenix", "mousesports", "FaZe", "OG", "fnatic",
                "EPG Family", "Evil Geniuses", "SKADE", "forZe", "Dignitas", "ENCE", "paiN", "AGO", "SAW", "Sprout"
        };
        String IMG_SDN_PREFIX = "https://img-cdn.hltv.org/teamlogo/";
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
                /* 21*/ "TsHpwfnv_gFU54oayT80gq.svg?ixlib=java-2.1.0&s=35ec85de80de2471f3b9aa88a31a458a",
                /* 22*/ "p6OXTVsTFEhOcbTJ8gmuP6.png?ixlib=java-2.1.0&w=50&s=41a619052f610fec335d17028d1f32af",
                /* 23*/ "YFPiz1uP4VOVNeqWOiFaOk.svg?ixlib=java-2.1.0&s=c62371c6760322616399d6975b845c87",
                /* 24*/ "Qnpb1nBNLJUCyf4fRMFbzr.svg?ixlib=java-2.1.0&s=a798b973c429361844ee174e07ae2401",
                /* 25*/ "w2EJ4p_IcYGMu0Cs2G4ZUn.png?ixlib=java-2.1.0&w=50&s=747bd21df637f066a412aaad3a30e4c0",
                /* 26*/ "-X8NoyWC_1gYqUHvZqcpkc.svg?ixlib=java-2.1.0&s=85bb9daa6f846fa097c5942f2565fdb8",
                /* 27*/ "iUUCFwCOFmOrwhB8q8smMg.svg?ixlib=java-2.1.0&s=1446e1cf3d02deb8190fe6efd14e4ce4",
                /* 28*/ "iUUCFwCOFmOrwhB8q8smMg.svg?ixlib=java-2.1.0&s=1446e1cf3d02deb8190fe6efd14e4ce4",
                /* 29*/ "7iWEFSeqhDdqbG4U2Su9Vy.png?ixlib=java-2.1.0&w=50&s=4adccb0378e285d75db4cd2f244f6abb",
                /* 30*/ "Ut8iEF66VCIbZGniMYn5jL.svg?ixlib=java-2.1.0&s=e6ae4904320fcbb566d098641e22a474",
        };
        Integer[] RATINGS = {
                993, 835, 639, 607, 596, 564, 536, 517, 490, 468, 432, 354, 344, 272, 252,
                249, 242, 225, 224, 188, 184, 166, 158, 153, 153, 151, 111, 104, 97, 91
        };
        for (int i = 0; i < 30; i++) {
            teamRepository.findByName(NAMES[i]).orElse(teamRepository.save(
                    new Team(NAMES[i], IMG_SDN_PREFIX + LOGOS[i], RATINGS[i], user)
            ));
        }
    }

    public void createTournaments() {
        String[] NAMES = {
                "DreamHack Masters Spring 2021",
                "Flashpoint 3",
                "EPIC League CIS 2021",
                "cs_summit 8",
                "ESEA Premier Season 37 North America",
                "ESEA Premier Season 37 Australia",
                "ESEA Premier Season 37 Europe",
                "Funspark ULTI 2021 Europe Regional Series 2",
                "IEM Summer 2021",
                "BLAST Premier Spring Final 2021",
                "Funspark ULTI 2021 Europe Playoffs 1",
                "IEM Cologne 2021 Play-In",
                "IEM Cologne 2021",
                "BLAST Premier Fall Groups 2021",
                "ESL Pro League Season 14",
                "DreamHack Open Atlanta 2021",
        };
        String IMG_SDN_PREFIX = "https://img-cdn.hltv.org/eventlogo/";
        String[] LOGOS = {
                "wZYnxtRyDPfSU3tWsfzbx7.png?ixlib=java-2.1.0&s=0194743bb96492b5d3e12ad81b7edb90",
                "hxW5vQbGVaPSiK4jxaPvQk.png?ixlib=java-2.1.0&w=50&s=0bc4cc62f12fda5f51c5c32531262387",
                "L5APycqKjSmouqShl3weiS.png?ixlib=java-2.1.0&s=d3b06ff8f45a45c8740eaf56cc4b52cd",
                "njRXjh3S3UnBvh-gtcbjFb.png?ixlib=java-2.1.0&s=e61843902bd9bbd0d50be58a6e430760",
                "Fu5w0q5fm_JedUt4Trlx39.png?ixlib=java-2.1.0&s=116ecf0d78dec2bcafafa861f8398c93",
                "Fu5w0q5fm_JedUt4Trlx39.png?ixlib=java-2.1.0&s=116ecf0d78dec2bcafafa861f8398c93",
                "Fu5w0q5fm_JedUt4Trlx39.png?ixlib=java-2.1.0&s=116ecf0d78dec2bcafafa861f8398c93",
                "5MsJVsP4IlP-9ReFaRCNBE.png?ixlib=java-2.1.0&s=0c781916b2c0f5e113bd17636fe99630",
                "dxrSBtNp6X_5sEX_RtI-9F.png?ixlib=java-2.1.0&w=50&s=4495ad07a9c3477df3ddaab1ca012d0e",
                "O8dyTstiXZp1wPIcOGi_GC.png?ixlib=java-2.1.0&w=50&s=12e9363167f3ca426565ebd59124a88a",
                "5MsJVsP4IlP-9ReFaRCNBE.png?ixlib=java-2.1.0&s=0c781916b2c0f5e113bd17636fe99630",
                "dxrSBtNp6X_5sEX_RtI-9F.png?ixlib=java-2.1.0&w=50&s=4495ad07a9c3477df3ddaab1ca012d0e",
                "dxrSBtNp6X_5sEX_RtI-9F.png?ixlib=java-2.1.0&w=50&s=4495ad07a9c3477df3ddaab1ca012d0e",
                "O8dyTstiXZp1wPIcOGi_GC.png?ixlib=java-2.1.0&w=50&s=12e9363167f3ca426565ebd59124a88a",
                "sMONFSLmFBkuylP-1aGbt_.png?ixlib=java-2.1.0&w=50&s=cb7e7aaf7e2ec69fff0eec8d9c4aea47",
                "OBYdYtX-x0vAyEMOgmtQ7P.png?ixlib=java-2.1.0&s=bc8ddffd243fbdc9f93b9114a28094d3",
        };
        for (int i = 0; i < 16; i++) {
            tournamentRepository.findByName(NAMES[i]).orElse(tournamentRepository.save(
                    new Tournament(NAMES[i], IMG_SDN_PREFIX + LOGOS[i], user)
            ));
        }
    }
}
