package coursework.tournamentbracketgenerator.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tournament_types")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString
public class BracketType extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String type;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<Bracket> brackets;
}
