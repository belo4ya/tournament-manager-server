package coursework.tournamentbracketgenerator.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bracket_nodes")
@EqualsAndHashCode(callSuper = true, exclude = { "bracket" })
@Data
@NoArgsConstructor
public class BracketNode extends BaseEntity {

    @Column(nullable = false)
    private Integer round;

    @ManyToOne
    @JoinColumn(name = "bracket_id", nullable = false)
    private Bracket bracket;

    @OneToMany(mappedBy = "bracketNode")
    private List<BracketNodeTeam> teams;
}
