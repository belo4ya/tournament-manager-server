package coursework.tournamentbracketgenerator.services.brackets;

import coursework.tournamentbracketgenerator.models.BracketType;
import coursework.tournamentbracketgenerator.models.Team;

import java.util.List;

public class TournamentDto {
    private String name;
    private String logo;
    private BracketType bracketType;
    private String seedType;
    private List<Team> teams;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public BracketType getBracketType() {
        return bracketType;
    }

    public void setBracketType(BracketType bracketType) {
        this.bracketType = bracketType;
    }

    public String getSeedType() {
        return seedType;
    }

    public void setSeedType(String seedType) {
        this.seedType = seedType;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return "TournamentDto{" +
                "name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", bracketType='" + bracketType + '\'' +
                ", seedType='" + seedType + '\'' +
                ", teams=" + teams +
                '}';
    }
}