package ua.kruart.workout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kruart.workout.model.Approach;
import ua.kruart.workout.repository.ApproachRepository;
import ua.kruart.workout.util.Checks;
import ua.kruart.workout.util.exception.InvalidParameterException;

import java.util.List;

/**
 * Implementation of the {@link ApproachService} interface
 *
 * Created by kruart on 23.06.2017.
 */
@Service
public class ApproachServiceImpl implements ApproachService {

    @Autowired
    private ApproachRepository repository;

    @Override
    public Approach save(Approach approach, int exerciseId, int userId) {
        Checks.checkParameter(repository.save(approach, exerciseId, userId), exerciseId);
        return approach;
    }

    @Override
    public void update(Approach approach, int exerciseId, int userId) throws InvalidParameterException {
        Checks.checkParameter(repository.save(approach, exerciseId, userId), approach.getId());
    }

    @Override
    public void delete(int id, int exerciseId, int userId) throws InvalidParameterException {
        Checks.checkParameter(repository.delete(id, exerciseId, userId), id);
    }

    @Override
    public Approach get(int id, int exerciseId, int userId) throws InvalidParameterException {
        Approach approach = repository.findById(id, exerciseId, userId);
        Checks.checkParameter(approach, id);
        return approach;
    }

    @Override
    public List<Approach> getAll(int exerciseId, int userId) {
        List<Approach> approachList = repository.findAll(exerciseId, userId);
        Checks.checkParameter(approachList != null, exerciseId);
        return approachList;
    }
}
