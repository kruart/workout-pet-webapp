package ua.kruart.workout.util;

import ua.kruart.workout.model.User;

/**
 * Contains some additional useful business logic over {@link User} entity
 *
 * @author  kruart on 11.07.2017.
 */
public class UserUtil {

    /**
     * Combines old and new users
     */
    public static User updateUser(User oldUser, User newUser) {
        oldUser.setName(newUser.getName());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPassword(PasswordUtil.encode(newUser.getPassword()));
        return oldUser;
    }
}
