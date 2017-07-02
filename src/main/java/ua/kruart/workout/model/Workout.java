package ua.kruart.workout.model;

import ua.kruart.workout.model.base.NamedEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * It's entity describes workout which includes one or more exercises
 *
 * @author kruart on 15.05.2017.
 */
@NamedQueries({
        @NamedQuery(name = "Workout.delete", query = "DELETE FROM Workout w WHERE w.id=:workoutId"),
        @NamedQuery(name = "Workout.findAll", query = "SELECT w FROM Workout w ORDER BY w.startWorkout DESC")
})
@Table(name = "tbl_workout")
@Entity
public class Workout extends NamedEntity {

    /**
     * Date when workout was started
     */
    @Column(name = "startWorkout")
    private LocalDateTime startWorkout;

    /**
     * Date when workout was ended
     */
    @Column(name = "endWorkout")
    private LocalDateTime endWorkout;

    /**
     * List of the exercises are included in workout
     */
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "workout")
    private List<Exercise> exerciseList;

    public Workout() {}

    public Workout(String name, LocalDateTime start) {
        this(null, name, null, start, null);
    }

    public Workout(Integer id, String name, List<Exercise> exerciseList, LocalDateTime start, LocalDateTime end) {
        super(id, name);
        this.exerciseList = exerciseList;
        this.startWorkout = start;
        this.endWorkout = end;
    }

    public LocalDateTime getStartWorkout() {
        return startWorkout;
    }

    public void setStartWorkout(LocalDateTime startWorkout) {
        this.startWorkout = startWorkout;
    }

    public LocalDateTime getEndWorkout() {
        return endWorkout;
    }

    public void setEndWorkout(LocalDateTime endWorkout) {
        this.endWorkout = endWorkout;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "startWorkout=" + startWorkout +
                ", endWorkout=" + endWorkout +
                ", exerciseList=" + exerciseList +
                '}';
    }
}
