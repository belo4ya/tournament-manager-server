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
public class Tournament extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 1024)
    private String logo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "tournament")
    private List<TournamentTeam> teams;

    @OneToMany(mappedBy = "tournament")
    private List<Bracket> brackets;

    public Tournament(String name, String logo, User user) {
        this.name = name;
        this.logo = logo;
        this.user = user;
    }
}
