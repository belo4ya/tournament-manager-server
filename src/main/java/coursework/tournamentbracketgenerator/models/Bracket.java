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
@ToString
public class Bracket extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @OneToMany(mappedBy = "bracket", cascade = CascadeType.ALL)
    private List<BracketNode> bracketNodes;
}
