package coursework.tournamentbracketgenerator.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bracket_nodes")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString
public class BracketNode extends BaseEntity {

    private Integer round;

    @ManyToOne
    @JoinColumn(name = "bracket_id")
    private Bracket bracket;

    @OneToMany(mappedBy = "bracketNode")
    private List<BracketNodeTeam> teams;
}
