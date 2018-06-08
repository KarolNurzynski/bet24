package pl.coderslab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity representing data stored in table accounts in the bet24 database.
 * Also used by Hibernate to build database and sql relations.
 * This entity is representing clinet acount with its number.
 */
@Entity
@Table(name="accounts")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountNumber;

    private boolean activeStatus=false;

    @OneToMany(mappedBy = "account")
    private List<Operation> operations;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="user_id")
    @JsonIgnore
    private User user;

}
