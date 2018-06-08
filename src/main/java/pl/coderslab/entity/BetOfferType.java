package pl.coderslab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity representing data stored in table bet_offer_types in the bet24 database.
 * Also used by Hibernate to build database and sql relations.
 *
 * This entity represents types of bet offers that could be placed - e.g. team A wins, draw..
 */
@Entity
@Table(name="betOfferTypes")
@Data
public class BetOfferType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "betOfferType")
    @JsonIgnore
    private List<BetOffer> betOffers;

}
