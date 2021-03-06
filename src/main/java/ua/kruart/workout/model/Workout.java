package ua.kruart.workout.model;

import org.springframework.format.annotation.DateTimeFormat;
import ua.kruart.workout.model.base.NamedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * It's entity describes workout which includes one or more exercises
 *
 * @author kruart on 15.05.2017.
 */
@NamedQueries({
        @NamedQuery(name = "Workout.getById", query = "SELECT w FROM Workout w WHERE w.id=:id AND w.user.id=:userId"),
        @NamedQuery(name = "Workout.delete", query = "DELETE FROM Workout w WHERE w.id=:workoutId AND w.user.id=:userId"),
        @NamedQuery(name = "Workout.findAll", query = "SELECT w FROM Workout w WHERE w.user.id=:userId ORDER BY w.startWorkout DESC")
})
@Table(name = "tbl_workout")
@Entity
public class Workout extends NamedEntity {

    /**
     * Date when workout was started
     */
    @Column(name = "startWorkout")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private LocalDateTime startWorkout;

    /**
     * Date when workout was ended
     */
    @Column(name = "endWorkout")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private LocalDateTime endWorkout;

    /**
     * List of the exercises are included in workout
     */
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "workout")
    private List<Exercise> exerciseList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
