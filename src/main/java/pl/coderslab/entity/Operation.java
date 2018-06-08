package pl.coderslab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing data stored in table operations in the bet24 database.
 * Also used by Hibernate to build database and sql relations.
 *
 * This entity represents oparation made on client account.
 */
@Entity
@Table(name="operations")
@Data
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description = "standard operation";

    private BigDecimal value;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
