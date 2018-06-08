package pl.coderslab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity representing data stored in table bet_offers in the bet24 database.
 * Also used by Hibernate to build database and sql relations.
 *
 * This ebtity represents a bet offer prepared by the service provider.
 * Bet offers are generated automatically after new or updated event appears. Calculus of odds is made automatically
 * by static methods of class {@link pl.coderslab.app.OddsCalculator}
 */
@Entity
@Table(name="betOffers")
@Data
public class BetOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal odds;

    private LocalDateTime published;

    private LocalDateTime finished;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(mappedBy = "betOffer")
    @JsonIgnore
    private List<Bet> bets;

    @ManyToOne
    @JoinColumn(name = "betOfferType_id")
    private BetOfferType betOfferType;

}
