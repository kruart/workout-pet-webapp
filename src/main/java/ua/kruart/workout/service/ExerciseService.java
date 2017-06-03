package ua.kruart.workout.service;

import ua.kruart.workout.model.Exercise;
import ua.kruart.workout.util.exception.InvalidParameterException;

import java.util.List;

/**
 * Contains functions for executing business logic over {@link Exercise} entity objects
 * and delegates data to the dao layer.
 * Actially, it's a Bridge between dao and controller layers.
 * It's an intermediate step to data validation and performing other business logic.
 *
 * @author kruart on 27.05.2017
 */
public interface ExerciseService {
    Exercise save(Exercise workout, int workoutId);

    void update(Exercise workout, int workoutId) throws InvalidParameterException;

    void delete(int id, int workoutId) throws InvalidParameterException;

    Exercise get(int id, int workoutId) throws InvalidParameterException;

    List<Exercise> getAll(int workoutId);
}
