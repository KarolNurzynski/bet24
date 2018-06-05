package pl.coderslab.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="account")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountNumber;

    @OneToMany(mappedBy = "account")
    private List<Operation> operations;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private User user;

}
