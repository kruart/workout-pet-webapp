package ua.kruart.workout.model;

import ua.kruart.workout.model.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * Describes one physical approach during the exercise
 *
 * @author kruart on 14.05.2017.
 */
@NamedQueries({
        @NamedQuery(name = "Approach.findById", query = "SELECT a FROM Approach a WHERE a.id=:id AND a.exercise.id=:exerciseId"),
        @NamedQuery(name = "Approach.delete", query = "DELETE FROM Approach a WHERE a.id=:id AND a.exercise.id=:exerciseId"),
        @NamedQuery(name = "Approach.findAll", query = "SELECT a FROM Approach a WHERE a.exercise.id=:exerciseId"),
})
@Table(name = "tbl_approach")
@Entity
public class Approach extends BaseEntity {

    /**
     * Repeats in one approach
     */
    @Column(name = "repeats")
    @Min(value = 0, message = "The value must be positive")
    private int repeats;

    /**
     * Tonnage of the current approach
     */
    @Column(name = "weight")
    @Min(value = 0, message = "The value must be positive")
    private float weight;

    /**
     * Distance of the current approach
     */
    @Column(name = "distance")
    @Min(value = 0, message = "The value must be positive")
    private float distance;

    /**
     * Time of execution a current approach in minutes
     */
    @Column(name = "time")
    @Min(value = 0, message = "The value must be positive")
    private Integer time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    public Approach() {}

    public Approach(Integer id, int repeats, float weight, float distance, Integer time) {
        super(id);
        this.repeats = repeats;
        this.weight = weight;
        this.distance = distance;
        this.time = time;
    }

    public Approach(Integer id, int repeats, float weight, float distance, Integer time, Exercise exercise) {
        this(id, repeats, weight, distance, time);
        this.exercise = exercise;
    }

    public int getRepeats() {
        return repeats;
    }

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    @Override
    public String toString() {
        return "Approach{" +
                "repeats=" + repeats +
                ", weight=" + weight +
                ", distance=" + distance +
                ", time=" + time +
                '}';
    }
}
