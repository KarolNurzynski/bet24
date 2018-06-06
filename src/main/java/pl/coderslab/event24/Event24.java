package pl.coderslab.event24;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name="event24")
@Data
public class Event24 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamA;

    private String teamB;

    private int scoreA=0;

    private int scoreB=0;

    private int strengthA;

    private int strengthB;

    private LocalDateTime startDate;

    private LocalTime timeLeft = LocalTime.of(1,30,0);


}
