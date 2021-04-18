package coursework.tournamentbracketgenerator.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bracket_nodes_teams")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString
public class BracketNodeTeam extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "bracket_node_id")
    private BracketNode bracketNode;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TournamentTeam team;
}
