package coursework.tournamentbracketgenerator.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bracket_types")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BracketType extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String type;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<Bracket> brackets;

    public BracketType(String type) {
        this.type = type;
    }
}
