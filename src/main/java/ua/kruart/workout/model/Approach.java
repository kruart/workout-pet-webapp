package ua.kruart.workout.model;

import ua.kruart.workout.model.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalTime;

/**
 * Describes one physical approach during the exercise
 *
 * @author kruart on 14.05.2017.
 */
@Table(name = "tbl_approach")
@Entity
public class Approach extends BaseEntity {

    /**
     * Repeats in one approach
     */
    @Column(name = "repeats")
    private int repeats;

    /**
     * Tonnage of the current approach
     */
    @Column(name = "weight")
    private float weight;

    /**
     * Distance of the current approach
     */
    @Column(name = "distance")
    private float distance;

    /**
     * Time of execution a current approach
     */
    @Column(name = "time")
    private LocalTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    public Approach() {}

    public Approach(Integer id, int repeats, float weight, float distance, LocalTime time) {
        super(id);
        this.repeats = repeats;
        this.weight = weight;
        this.distance = distance;
        this.time = time;
    }

    public Approach(Integer id, int repeats, float weight, float distance, LocalTime time, Exercise exercise) {
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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
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
