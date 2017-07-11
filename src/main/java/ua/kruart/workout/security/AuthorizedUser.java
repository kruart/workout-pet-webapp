package ua.kruart.workout.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.kruart.workout.model.User;

import static java.util.Objects.requireNonNull;

/**
 * Represents an authorized user.
 *
 * @author  kruart on 10.07.2017.
 */
public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.user = user;
    }

    /**
     * Retrieves our authorized user from SecurityContextHolder
     */
    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth == null) {
            return null;
        }
        Object user = auth.getPrincipal();
        return (user instanceof AuthorizedUser) ? (AuthorizedUser) user : null;
    }

    /**
     * Returns authorized user object or throws NullPointerException if not found
     */
    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    /**
     * Returns the ID of the authorized user
     */
    public static Integer getAuthUserId() {
        return get().user.getId();
    }

    /**
     * Updates the authorized user after the user changes his own profile
     */
    public void updateAuthorizedUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
