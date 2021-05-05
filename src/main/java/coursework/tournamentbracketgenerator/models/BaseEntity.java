package coursework.tournamentbracketgenerator.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Calendar;

@MappedSuperclass
@Data
@NoArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "HH:mm dd/MM/yyyy")
    @JsonFormat(pattern = "HH:mm dd/MM/yyyy")
    @CreationTimestamp
    private Calendar createdDate;

    @DateTimeFormat(pattern = "HH:mm dd/MM/yyyy")
    @JsonFormat(pattern = "HH:mm dd/MM/yyyy")
    @UpdateTimestamp
    private Calendar lastModifiedDate;
}
