package ua.kruart.workout.model;

import java.time.LocalTime;

/**
 * Describes one physical approach during the exercise
 *
 * @author kruart on 14.05.2017.
 */
public class Approach extends BaseEntity {

    /**
     * Repeats in one approach
     */
    private int repeats;

    /**
     * Tonnage of the current approach
     */
    private float weight;

    /**
     * Distance of the current approach
     */
    private float distance;

    /**
     * Time of the current approach
     */
    private LocalTime time;

    public Approach() {}

    public Approach(int id, int repeats, float weight, float distance, LocalTime time) {
        super(id);
        this.repeats = repeats;
        this.weight = weight;
        this.distance = distance;
        this.time = time;
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
