package coursework.tournamentbracketgenerator.projections;

import lombok.Data;

@Data
public class JwtToken {
    private final String token;

    public JwtToken(String token) {
        this.token = token;
    }
}
