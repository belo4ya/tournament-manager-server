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
@ToString
public class TournamentTeam extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "team")
    private List<BracketNodeTeam> bracketNodes;
}
