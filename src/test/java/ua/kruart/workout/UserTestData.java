package ua.kruart.workout;

import ua.kruart.workout.model.Role;
import ua.kruart.workout.model.User;

/**
 * Contains test data for tests
 *
 * @author  kruart on 14.07.2017.
 */
public class UserTestData {
    public static User USER = new User(1, "user", "user@ukr.net", "password", true, Role.ROLE_USER);
}
