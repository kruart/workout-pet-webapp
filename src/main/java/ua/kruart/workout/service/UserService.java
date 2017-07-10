package ua.kruart.workout.service;

import ua.kruart.workout.model.User;
import ua.kruart.workout.util.exception.InvalidParameterException;

import java.util.List;

/**
 * /**
 * Contains functions for executing business logic over {@link User} entity objects
 * and delegates entities to the dao layer.
 * Actially, it's a Bridge between dao and controller layers.
 * It's an intermediate step to data validation and performing other business logic.
 *
 * @author kruart on 10.07.2017.
 */
public interface UserService {

    User save(User user);

    void delete(int id) throws InvalidParameterException;

    User get(int id) throws InvalidParameterException;

    User getByEmail(String email) throws InvalidParameterException;


    List<User> getAll();

    void update(User user);

    void enable(int id, boolean enable);
}

