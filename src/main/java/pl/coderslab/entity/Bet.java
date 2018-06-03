package pl.coderslab.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "betOffer_id")
    private BetOffer betOffer;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

}
