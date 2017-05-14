package ua.kruart.workout.model;

import java.util.List;

/**
 * Entity that describes exercise
 *
 * @author kruart on 14.05.2017.
 */
public class Exercise {

    /**
     * Unique identifier
     */
    private int id;

    /**
     * Contains a list of paths to the exercise images
     */
    private List<String> images;

    /**
     * Configuration of exercise measures(weight, distance, repeats, time)
     */
    private ExerciseConfiguration conf;

    /**
     * Description of the exercise
     */
    private ExerciseDescription description;

    /**
     * Comment of the exercise
     */
    private String comment;
}
