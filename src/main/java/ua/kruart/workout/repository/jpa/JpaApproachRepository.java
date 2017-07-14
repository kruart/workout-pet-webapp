package ua.kruart.workout.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.kruart.workout.model.Approach;
import ua.kruart.workout.model.Exercise;
import ua.kruart.workout.repository.ApproachRepository;
import ua.kruart.workout.repository.WorkoutRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * JPA implementation of the {@link ua.kruart.workout.repository.ApproachRepository} interface
 *
 * Created by kruart on 21.06.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JpaApproachRepository implements ApproachRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private WorkoutRepository workoutRepo;

    @Transactional
    @Override
    public Approach save(Approach approach, int exerciseId, int userId) {
        Exercise exerciseRef = em.getReference(Exercise.class, exerciseId);
        approach.setExercise(exerciseRef);

        if (!isExerciseBelongsToUser(exerciseId, userId)) {
            return null;    //if not return null
        }

        if (approach.isNew()) {
            em.persist(approach);
            return approach;
        } else {
            return findById(approach.getId(), exerciseId, userId) == null ? null : em.merge(approach);
        }
    }

    @Override
    public Approach findById(int id, int exerciseId, int userId) {
        List<Approach> approachTypedQuery = em.createNamedQuery("Approach.findById", Approach.class)
                .setParameter("id", id)
                .setParameter("exerciseId", exerciseId)
                .setParameter("userId", userId)
                .getResultList();
        return DataAccessUtils.singleResult(approachTypedQuery);
    }

    @Transactional
    @Override
    public boolean delete(int id, int exerciseId, int userId) {
        return findById(id, exerciseId, userId) != null &&
                em.createNamedQuery("Approach.delete")
                        .setParameter("id", id)
                        .setParameter("exerciseId", exerciseId)
                        .executeUpdate() != 0;
    }

    @Override
    public List<Approach> findAll(int exerciseId, int userId) {
        if (!isExerciseBelongsToUser(exerciseId, userId)) {
            return null;    //if not return null
        }

        return em.createNamedQuery("Approach.findAll", Approach.class)
                .setParameter("exerciseId", exerciseId)
                .setParameter("userId", userId)
                .getResultList();
    }

    private boolean isExerciseBelongsToUser(int exerciseId, int userId) {
        return em.createQuery(
                "select w from Workout w where w.user.id=:userId AND w.id=(select e.workout.id from Exercise e where e.id=:exerciseId)")
                .setParameter("userId", userId)
                .setParameter("exerciseId", exerciseId)
                .getResultList().size() != 0;
    }
}
