package ua.kruart.workout.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by kruart on 15.05.2017.
 */
public class Workout {

    private int id;

    private LocalDateTime startWorkout;

    private LocalDateTime endWorkout;

    private List<Exercise> exerciseList;

    public Workout() {}

    public Workout(int id, List<Exercise> exerciseList) {
        this.id = id;
        this.exerciseList = exerciseList;
        this.startWorkout = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Workout workout = (Workout) o;

        return id == workout.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "id=" + id +
                ", startWorkout=" + startWorkout +
                ", endWorkout=" + endWorkout +
                ", exerciseList=" + exerciseList +
                '}';
    }
}
