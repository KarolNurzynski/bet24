package pl.coderslab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing data stored in table bets in the bet24 database.
 * Also used by Hibernate to build database and sql relations.
 * This entity represents a bet placed by client (user).
 * Each bet is related with one event and one bet offer.
 */
@Entity
@Table(name="bets")
@Data
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal stake = BigDecimal.valueOf(0);

    private LocalDateTime timeMade;

    private LocalDateTime timeEnd;

    private int paidStatus = 0; //0 = not paid; 1 = already paid

    @ManyToOne
    @JoinColumn(name = "betOffer_id")
    private BetOffer betOffer;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnore
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
