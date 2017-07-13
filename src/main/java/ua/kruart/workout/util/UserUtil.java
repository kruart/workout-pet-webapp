package ua.kruart.workout.util;

import ua.kruart.workout.model.Role;
import ua.kruart.workout.model.User;

import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

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

    /**
     * Prepares a user object for saving. Used when a user tries to register.
     */
    public static User prepareForSave(User user) {
        user.setId(null);
        user.setRoles(new HashSet<>(Collections.singletonList(Role.ROLE_USER)));
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        return user;
    }

    /**
     * Prepares a custom object for saving. Used when a user tries to register via oauth authorization.
     */
    public static User prepareForSaveOauth(String userName, String email) {
        return new User(null,
                userName,
                email.toLowerCase(),
                PasswordUtil.encode(UUID.randomUUID().toString()),
                true,
                new HashSet<>(Collections.singletonList(Role.ROLE_USER))
        );
    }
}
