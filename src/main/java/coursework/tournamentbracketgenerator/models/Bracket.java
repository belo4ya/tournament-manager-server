package coursework.tournamentbracketgenerator.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brackets")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Bracket extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private BracketType type;

    @OneToMany(mappedBy = "bracket", cascade = CascadeType.ALL)
    private List<BracketNode> bracketNodes;
}
