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
    public Approach save(Approach approach, int exerciseId) {
        Checks.checkParameter(repository.save(approach, exerciseId), exerciseId);
        return approach;
    }

    @Override
    public void update(Approach approach, int exerciseId) throws InvalidParameterException {
        Checks.checkParameter(repository.save(approach, exerciseId), approach.getId());
    }

    @Override
    public void delete(int id, int exerciseId) throws InvalidParameterException {
        Checks.checkParameter(repository.delete(id, exerciseId), id);
    }

    @Override
    public Approach get(int id, int exerciseId) throws InvalidParameterException {
        Approach approach = repository.findById(id, exerciseId);
        Checks.checkParameter(approach, id);
        return approach;
    }

    @Override
    public List<Approach> getAll(int exerciseId) {
        List<Approach> approachList = repository.findAll(exerciseId);
        Checks.checkParameter(approachList != null, exerciseId);
        return approachList;
    }
}
