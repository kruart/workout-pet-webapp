package ua.kruart.workout.service;

import ua.kruart.workout.model.Workout;
import ua.kruart.workout.util.exception.InvalidParameterException;

import java.util.List;

/**
 * Contains functions for executing business logic over {@link Workout} entity objects
 *
 * @author kruart on 27.05.2017
 */
public interface WorkoutService {

    Workout save(Workout user);

    void update(Workout user);

    void delete(int id) throws InvalidParameterException;

    Workout get(int id) throws InvalidParameterException;

    List<Workout> getAll();
}
