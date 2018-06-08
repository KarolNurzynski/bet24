package pl.coderslab.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Entity representing data stored in table roles in the bet24 database.
 * Also used by Hibernate to build database and sql relations.
 *
 * This is a standard role entity required by Spring Security
 */
@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;

    @Column(name = "role")
    private String name;
}