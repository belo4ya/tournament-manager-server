package coursework.tournamentbracketgenerator.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.Calendar;

@Projection(name = "bracketType", types = { Tournament.class })
public interface TournamentProjection {
    Long getId();

    @JsonFormat(pattern = "HH:mm dd/MM/yyyy")
    Calendar getCreatedDate();

    @JsonFormat(pattern = "HH:mm dd/MM/yyyy")
    Calendar getLastModifiedDate();

    String getName();

    String getLogo();

    @Value("#{target?.brackets?.get(0).type.type}")
    String getBracketType();

    @Value("#{target?.teams?.size()}")
    Integer getTotalTeams();
}
