package ua.kruart.workout.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.kruart.workout.model.Workout;
import ua.kruart.workout.repository.WorkoutRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
        TypedQuery<Workout> query = this.entityManager.createQuery("SELECT o FROM Workout o LEFT JOIN FETCH o.exerciseList WHERE o.id = " + workoutId, Workout.class);
        return query.getResultList().size() > 0 ? query.getSingleResult() : null;
//        Workout workout = entityManager.find(Workout.class, workoutId);
//        workout.getExerciseList().size();
//        return workout;
    }

    @Override
    @Transactional
    public boolean delete(int workoutId) {
//        entityManager.createQuery("DELETE FROM Workout w WHERE w.id=:id").setParameter("id", workoutId).executeUpdate();
        return entityManager
                .createQuery("DELETE FROM Workout w WHERE w.id=" + workoutId)
                .executeUpdate() != 0;
    }

    @Override
    public List<Workout> findAll() {
        return entityManager
                .createQuery("SELECT w from Workout w ORDER BY w.startWorkout DESC", Workout.class)
                .getResultList();
    }
}
