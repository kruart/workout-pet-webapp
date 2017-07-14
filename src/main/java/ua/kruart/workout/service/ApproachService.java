package ua.kruart.workout.service;

import ua.kruart.workout.model.Approach;
import ua.kruart.workout.util.exception.InvalidParameterException;

import java.util.List;

/**
 * Contains functions for executing business logic over {@link Approach} entity objects
 * and delegates entities to the dao layer.
 * Actially, it's a 'bridge' between dao and controller layers.
 * It's an intermediate step to data validation and performing other business logic.
 *
 * Created by kruart on 23.06.2017.
 */
public interface ApproachService {

    Approach save(Approach approach, int exerciseId, int userId);

    void update(Approach approach, int exerciseId, int userId) throws InvalidParameterException;

    void delete(int id, int exerciseId, int userId) throws InvalidParameterException;

    Approach get(int id, int exerciseId, int userId) throws InvalidParameterException;

    List<Approach> getAll(int exerciseId, int userId);
}
