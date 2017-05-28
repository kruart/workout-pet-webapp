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
    public Workout save(Workout workout) {
        return repository.save(workout);
    }

    @Override
    public void update(Workout workout) {
        Checks.checkParameter(repository.save(workout), "Not found entity");
    }

    @Override
    public void delete(int id) {
        Checks.checkParameter(repository.delete(id), "Not found entity");
    }

    @Override
    public Workout get(int id) {
        Workout byId = repository.findById(id);
        Checks.checkParameter(byId, "Not found entity");
        return byId;
    }

    @Override
    public List<Workout> getAll() {
        return repository.findAll();
    }
}
