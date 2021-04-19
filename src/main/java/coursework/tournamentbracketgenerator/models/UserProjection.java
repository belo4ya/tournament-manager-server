package coursework.tournamentbracketgenerator.models;

import org.springframework.data.rest.core.config.Projection;

import java.util.Calendar;

@Projection(name = "noPassword", types = { User.class })
public interface UserProjection {
    Long getId();
    Calendar getCreatedDate();
    Calendar getLastModifiedDate();
    String getUsername();
}
