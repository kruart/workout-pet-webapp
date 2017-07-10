package ua.kruart.workout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kruart.workout.model.Exercise;
import ua.kruart.workout.repository.ExerciseRepository;
import ua.kruart.workout.util.Checks;
import ua.kruart.workout.util.exception.InvalidParameterException;

import java.util.List;

/**
 * Implementation of the {@link ExerciseService} interface
 *
 * Created by kruart on 03.06.2017.
 */
@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository repository;

    @Override
    public Exercise save(Exercise exercise, int workoutId) {
        Checks.checkParameter(repository.save(exercise, workoutId), workoutId);
        return exercise;
    }

    @Override
    public void update(Exercise exercise, int workoutId) throws InvalidParameterException {
        Checks.checkParameter(repository.save(exercise, workoutId), exercise.getId());
    }

    @Override
    public void delete(int id, int workoutId) throws InvalidParameterException {
        Checks.checkParameter(repository.delete(id, workoutId), id);
    }

    @Override
    public Exercise get(int id, int workoutId) throws InvalidParameterException {
        Exercise byId = repository.findById(id, workoutId);
        Checks.checkParameter(byId, id);
        return byId;
    }

    @Override
    public List<Exercise> getAll(int workoutId) {
        List<Exercise> exerciseList = repository.findAll(workoutId);
        Checks.checkParameter(exerciseList != null, workoutId);
        return exerciseList;
    }
}
