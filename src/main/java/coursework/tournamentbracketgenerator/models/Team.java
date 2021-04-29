package coursework.tournamentbracketgenerator.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teams")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString
public class Team extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(length = 1024)
    private String logo;

    private Integer rating = 0;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "team")
    private List<TournamentTeam> tournaments;

    public Team(String name, String logo, Integer rating, User user) {
        this.name = name;
        this.logo = logo;
        this.rating = rating;
        this.user = user;
    }
}
