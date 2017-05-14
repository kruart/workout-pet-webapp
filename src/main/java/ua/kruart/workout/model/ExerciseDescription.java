package ua.kruart.workout.model;

import java.util.Set;

/**
 * Contains description of the {@link Exercise} object
 *
 * @author kruart on 14.05.2017.
 */
public class ExerciseDescription {

    /**
     * Target muscle of the exercise
     */
    private Muscle target_muscle;

    /**
     * Additional muscles that are involved in the exercise
     */
    private Set<Muscle> option_muscles;

    /**
     * Type of complexity: Basic or Isolating
     */
    private String type;

    /**
     * Simple, Middle, Hard
     */
    private String complexity;

    /**
     * Name of the exercise
     */
    private String name;

    /**
     * Description of the exercise. How to do it correctly
     */
    private String description;
}
