package ua.kruart.workout.repository;

import ua.kruart.workout.model.Approach;

import java.util.List;

/**
 *  Defines CRUD methods to access {@link Approach} objects in the persistent storage
 *
 * @author kruart on 01.06.2017.
 */
public interface ApproachRepository {

    /**
     * Saves(creates or modifies) specified {@link Approach} instance
     * @param id unique identifier of {@link Approach} object
     * @param exerciseId unique identifier of {@link ua.kruart.workout.model.Exercise} instance to which {@link Approach} belongs
     * @return null if updated object is not found or {@code id} is not associated with {@code exerciseId}
     */
    Approach save(int id, int exerciseId);

    /**
     * Returns {@link Approach} with specified identifier.
     * @param id unique identifier of {@link Approach}
     * @param exerciseId unique identifier of {@link ua.kruart.workout.model.Exercise} instance to which {@link Approach} belongs
     * @return null if no {@code id} exists with such identifier or if {@code id} is not associated with {@code exerciseId}
     */
    Approach findById(int id, int exerciseId);

    /**
     * Delete approach with specified identifier
     * @param id unique identifier of {@link Approach}
     * @param exerciseId unique identifier of {@link ua.kruart.workout.model.Exercise} instance to which {@link Approach} belongs
     * @return false if not found
     */
    boolean delete(int id, int exerciseId);

    /**
     * Returns list of the {@link Approach} objects
     * @return
     */
    List<Approach> findAll(int exerciseId);
}
