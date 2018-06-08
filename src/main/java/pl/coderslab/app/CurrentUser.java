package pl.coderslab.app;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


/**
 * Current User class extends Spring {@link User} class by adding a new field user of type User (entity which stores data of users).
 * This gives us a class which combines both custom user fields and fields required by Spring Security
 */
public class CurrentUser extends User {
    private final pl.coderslab.entity.User user;
    public CurrentUser(String username, String password, Collection<?
                extends GrantedAuthority> authorities, pl.coderslab.entity.User user) {
        super(username, password, authorities);
        this.user = user;
    }
    public pl.coderslab.entity.User getUser() {
        return user;
    }
}
