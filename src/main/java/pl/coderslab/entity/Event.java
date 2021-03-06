package pl.coderslab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Entity representing data stored in table events in the bet24 database.
 * Also used by Hibernate to build database and sql relations.
 *
 * This entity represents an event - football match
 */
@Entity
@Table(name="events")
@Data
public class Event {

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

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<BetOffer> betOffers;

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<Bet> bets;


}
