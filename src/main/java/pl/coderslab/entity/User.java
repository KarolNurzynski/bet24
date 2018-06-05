package pl.coderslab.entity;

import lombok.Data;
import pl.coderslab.validation.EmailKN;
import pl.coderslab.validation.ValidUsernamePassword;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(groups = {Default.class})
    @Column(unique = true)
    private String firstName;

    @NotEmpty(groups = {Default.class})
    @Column(unique = true)
    private String lastName;

    @NotEmpty(groups = {Default.class})
    @EmailKN(groups = {Default.class})
    @Column(unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    @NotEmpty(groups = {ValidUsernamePassword.class, Default.class})
    private String username;

    @NotEmpty(groups = {ValidUsernamePassword.class, Default.class})
    private String password;

    @NotNull(groups = {Default.class})
    private int enabled = 0;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Bet> bets;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = use be"account_id")
//    private Account account;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts;

}
