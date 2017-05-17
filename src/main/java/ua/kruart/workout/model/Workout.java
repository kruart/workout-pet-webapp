package ua.kruart.workout.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

/**
 * It's entity describes workout which includes one or more exercises
 *
 * @author kruart on 15.05.2017.
 */
@Table(name = "tbl_workout")
@Entity
public class Workout extends NamedEntity {

    /**
     * Date when workout was started
     */
    private LocalDateTime startWorkout;

    /**
     * Date when workout was ended
     */
    private LocalDateTime endWorkout;

    /**
     * List of the exercises are included in workout
     */
    private List<Exercise> exerciseList;

    public Workout() {}

    public Workout(int id, String name, List<Exercise> exerciseList) {
        super(id, name);
        this.exerciseList = exerciseList;
        this.startWorkout = LocalDateTime.now();
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
