package ua.kruart.workout.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.kruart.workout.model.Exercise;
import ua.kruart.workout.model.Workout;
import ua.kruart.workout.repository.ExerciseRepository;
import ua.kruart.workout.repository.WorkoutRepository;

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
    @Autowired
    private WorkoutRepository workoutRepo;

    @Override
    @Transactional
    public Exercise save(Exercise exercise, int workoutId, int userId) {
        Workout workoutRef = entityManager.getReference(Workout.class, workoutId);
        exercise.setWorkout(workoutRef);
        if(!isWorkoutBelongsToUser(workoutId, userId)) {
            return null;    //if not return null
        }

        if (exercise.isNew()) {
            entityManager.persist(exercise);
            return exercise;
        } else {
            return findById(exercise.getId(), workoutId, userId) == null ? null : entityManager.merge(exercise);
        }
    }

    @Override
    public Exercise findById(int id, int workoutId, int userId) {
        List<Exercise> exerciseList = entityManager.createNamedQuery("Exercise.findById", Exercise.class)
                .setParameter("id", id)
                .setParameter("workoutId", workoutId)
                .setParameter("userId", userId)
                .getResultList();
        return DataAccessUtils.singleResult(exerciseList);
    }

    @Override
    @Transactional
    public boolean delete(int id, int workoutId, int userId) {
        return findById(id, workoutId, userId) != null &&
                entityManager.createNamedQuery("Exercise.delete")
                        .setParameter("id", id)
                        .setParameter("workoutId", workoutId)
                        .executeUpdate() != 0;
    }

    @Override
    public List<Exercise> findAll(int workoutId, int userId) {
        if (!isWorkoutBelongsToUser(workoutId, userId)) {
            return null;    //if not return null
        }

        return entityManager.createNamedQuery("Exercise.findAll", Exercise.class)
                .setParameter("userId", userId)
                .setParameter("workoutId", workoutId)
                .getResultList();

    }

    private boolean isWorkoutBelongsToUser(int workoutId, int userId) {
        return workoutRepo.findById(workoutId, userId) != null;
    }
}
