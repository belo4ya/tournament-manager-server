package coursework.tournamentbracketgenerator.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tournaments_teams")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TournamentTeam extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @OneToMany(mappedBy = "tournamentTeam")
    private List<BracketNodeTeam> bracketNodes;
}
