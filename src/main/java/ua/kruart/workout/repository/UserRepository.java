package ua.kruart.workout.repository;

import ua.kruart.workout.model.User;

import java.util.List;

/**
 * Defines CRUD methods to access {@link User} objects in the persistent storage
 *
 * @author  kruart on 10.07.2017.
 */
public interface UserRepository {

    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();
}
