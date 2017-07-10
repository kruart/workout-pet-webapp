package ua.kruart.workout.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Represents the rights that the user has
 *
 * @author kruart on 10.07.2017.
 */
public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }
}
