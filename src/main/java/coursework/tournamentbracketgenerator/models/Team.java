package coursework.tournamentbracketgenerator.models;

import com.fasterxml.jackson.annotation.JsonFilter;
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

    @Column(unique = true)
    private String name;

    private String logo;

    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "team")
    private List<TournamentTeam> tournaments;
}
