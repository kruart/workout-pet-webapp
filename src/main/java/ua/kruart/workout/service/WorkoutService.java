package ua.kruart.workout.service;

import ua.kruart.workout.model.Workout;
import ua.kruart.workout.util.exception.InvalidParameterException;

import java.util.List;

/**
 * Contains functions for executing business logic over {@link Workout} entity objects
 * and delegates entities to the dao layer.
 * Actially, it's a Bridge between dao and controller layers.
 * It's an intermediate step to data validation and performing other business logic.
 *
 * @author kruart on 27.05.2017
 */
public interface WorkoutService {

    Workout save(Workout workout, int userId);

    void update(Workout workout, int userId) throws InvalidParameterException;

    void delete(int id, int userId) throws InvalidParameterException;

    Workout get(int id, int userId) throws InvalidParameterException;

    List<Workout> getAll(int userId);
}
