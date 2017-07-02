package ua.kruart.workout.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.kruart.workout.model.Exercise;
import ua.kruart.workout.model.Workout;
import ua.kruart.workout.repository.ExerciseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * JPA implementation of the {@link ExerciseRepository} interface
 *
 * @author kruart on 03.06.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JpaExerciseRepository implements ExerciseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Exercise save(Exercise exercise, int workoutId) {
        Workout workoutRef = entityManager.getReference(Workout.class, workoutId);
        exercise.setWorkout(workoutRef);

        if (exercise.isNew()) {
            entityManager.persist(exercise);
            return exercise;
        } else {
            return findById(exercise.getId(), workoutId) == null ? null : entityManager.merge(exercise);
        }
    }

    @Override
    public Exercise findById(int id, int workoutId) {
        List<Exercise> exerciseList = entityManager.createNamedQuery("Exercise.findById", Exercise.class)
                .setParameter("id", id)
                .setParameter("workoutId", workoutId)
                .getResultList();
        return DataAccessUtils.singleResult(exerciseList);
    }

    @Override
    @Transactional
    public boolean delete(int id, int workoutId) {
        return entityManager.createNamedQuery("Exercise.delete")
                .setParameter("id", id)
                .setParameter("workoutId", workoutId)
                .executeUpdate() != 0;
    }

    @Override
    public List<Exercise> findAll(int workoutId) {
        return entityManager.createNamedQuery("Exercise.findAll", Exercise.class)
                .setParameter("id", workoutId)
                .getResultList();

    }
}
