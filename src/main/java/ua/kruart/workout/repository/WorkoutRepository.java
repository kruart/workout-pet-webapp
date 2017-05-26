package ua.kruart.workout.repository;

import ua.kruart.workout.model.Workout;

import java.util.List;

/**
 *  Defines CRUD methods to access {@link Workout} objects in the persistent storage
 *
 * @author kruart on 22.05.2017.
 */
public interface WorkoutRepository {

    /**
     * Saves(creates or modifies) specified workout instance
     * @param workout object which is necessary to save or update
     * @return null if updated object is not found
     */
    Workout save(Workout workout);

    /**
     * Returns workout with specified identifier. If no workout exists with such identifier
     * then null is returned
     * @param workoutId
     * @return null if no found
     */
    Workout findById(int workoutId);

    /**
     * Delete workout with specified identifier
     * @param workoutId
     * @return false if not found
     */
    boolean delete(int workoutId);

    /**
     * Returns all the workout objects
     * @return
     */
    List<Workout> findAll();
}
