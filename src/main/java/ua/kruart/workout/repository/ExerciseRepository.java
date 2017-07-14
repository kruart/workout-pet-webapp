package ua.kruart.workout.repository;

import ua.kruart.workout.model.Exercise;

import java.util.List;

/**
 *  Defines CRUD methods to access {@link Exercise} objects in the persistent storage
 *
 * @author kruart on 01.06.2017.
 */
public interface ExerciseRepository {

    /**
     * Saves(creates or modifies) specified exercise instance
     * @param exercise object which is necessary to save or update
     * @param workoutId unique identifier of {@link ua.kruart.workout.model.Workout} instance which {@link Exercise} belongs to
     * @return null if updated object is not found or {@code id} is not associated with {@code workoutId}
     */
    Exercise save(Exercise exercise, int workoutId, int userId);

    /**
     * Returns {@link Exercise} with specified identifier.
     * @param id unique identifier of {@link Exercise}
     * @param workoutId unique identifier of {@link ua.kruart.workout.model.Workout} instance which {@link Exercise} belongs to
     * @return if no {@link Exercise} exists with such identifier
     * or if {@code id} is not associated with {@code exerciseId} then null is returned
     */
    Exercise findById(int id, int workoutId, int userId);

    /**
     * Delete exercise with specified identifier
     * @param id unique identifier of the {@link Exercise}
     * @param workoutId unique identifier of {@link ua.kruart.workout.model.Workout} instance which {@link Exercise} belongs to
     * @return false if not found
     */
    boolean delete(int id, int workoutId, int userId);

    /**
     * Returns all the {@link Exercise} objects which belong to {@code workoutId}
     * @param workoutId unique identifier of {@link ua.kruart.workout.model.Workout} instance which {@link Exercise} belongs to
     * @return
     */
    List<Exercise> findAll(int workoutId, int userId);


}
