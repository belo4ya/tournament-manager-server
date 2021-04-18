package coursework.tournamentbracketgenerator.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tournament_types")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString
public class TournamentType extends BaseEntity {
    private String type;
}
