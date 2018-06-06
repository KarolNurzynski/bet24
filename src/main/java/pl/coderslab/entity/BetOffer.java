package pl.coderslab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
