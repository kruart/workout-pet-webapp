package ua.kruart.workout.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.kruart.workout.model.Workout;
import ua.kruart.workout.repository.WorkoutRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * JPA implementation of the {@link WorkoutRepository} interface
 *
 * @author kruart on 22.05.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JpaWorkoutRepository implements WorkoutRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Workout save(Workout workout) {
        if (workout.isNew()) {
            entityManager.persist(workout);
            return workout;
        } else {
            return findById(workout.getId()) == null ? null : entityManager.merge(workout);
        }
    }

    @Override
    public Workout findById(int workoutId) {
        return entityManager.find(Workout.class, workoutId);
    }

    @Override
    @Transactional
    public boolean delete(int workoutId) {
        return entityManager
                .createNamedQuery("Workout.delete")
                .setParameter("workoutId", workoutId)
                .executeUpdate() != 0;
    }

    @Override
    public List<Workout> findAll() {
        return entityManager
                .createNamedQuery("Workout.findAll", Workout.class)
                .getResultList();
    }
}
