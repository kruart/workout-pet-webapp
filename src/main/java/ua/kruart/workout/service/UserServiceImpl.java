package ua.kruart.workout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kruart.workout.model.User;
import ua.kruart.workout.repository.UserRepository;
import ua.kruart.workout.util.Checks;
import ua.kruart.workout.util.exception.InvalidParameterException;

import java.util.List;

/**
 * Implementation of the {@link UserService} interface
 *
 * @author kruart on 10.07.2017.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void delete(int id) {
        Checks.checkParameter(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws InvalidParameterException {
        User user = repository.get(id);
        Checks.checkParameter(user, id);
        return user;
    }

    @Override
    public User getByEmail(String email) throws InvalidParameterException {
        return Checks.checkParameter(repository.getByEmail(email), "email=" + email);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    public void update(User user) {
        repository.save(user);
    }

    @Override
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        repository.save(user);
    }
}
