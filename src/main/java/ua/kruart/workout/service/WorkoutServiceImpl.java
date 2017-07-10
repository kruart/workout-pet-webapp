package ua.kruart.workout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kruart.workout.model.Workout;
import ua.kruart.workout.repository.WorkoutRepository;
import ua.kruart.workout.util.Checks;

import java.util.List;

/**
 * Implementation of the {@link WorkoutService} interface
 *
 * @author kruart on 27.05.2017.
 */
@Service
public class WorkoutServiceImpl implements WorkoutService {

    @Autowired
    private WorkoutRepository repository;

    @Override
    public Workout save(Workout workout, int userId) {
        return repository.save(workout, userId);
    }

    @Override
    public void update(Workout workout, int userId) {
        Checks.checkParameter(repository.save(workout, userId), workout.getId());
    }

    @Override
    public void delete(int id, int userId) {
        Checks.checkParameter(repository.delete(id, userId), id);
    }

    @Override
    public Workout get(int id, int userId) {
        Workout byId = repository.findById(id, userId);
        Checks.checkParameter(byId, id);
        return byId;
    }

    @Override
    public List<Workout> getAll(int userId) {
        return repository.findAll(userId);
    }
}
