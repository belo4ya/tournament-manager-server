package coursework.tournamentbracketgenerator.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tournaments")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString
public class Tournament extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "tournament")
    private List<TournamentTeam> teams;

    @OneToMany(mappedBy = "tournament")
    private List<Bracket> brackets;
}
