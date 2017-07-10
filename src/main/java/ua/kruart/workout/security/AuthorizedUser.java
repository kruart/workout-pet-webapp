package ua.kruart.workout.security;

/**
 * Represents an authorized user.
 *
 * @author  kruart on 10.07.2017.
 */
public class AuthorizedUser {

    private static Integer loggedUserId = 1;

    public static Integer getLoggedUserId() {
        return loggedUserId;
    }
}
