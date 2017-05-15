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

    public ExerciseDescription() {}

    public ExerciseDescription(Muscle target_muscle, Set<Muscle> option_muscles, String type, String complexity, String name, String description) {
        this.target_muscle = target_muscle;
        this.option_muscles = option_muscles;
        this.type = type;
        this.complexity = complexity;
        this.name = name;
        this.description = description;
    }

    public Muscle getTarget_muscle() {
        return target_muscle;
    }

    public void setTarget_muscle(Muscle target_muscle) {
        this.target_muscle = target_muscle;
    }

    public Set<Muscle> getOption_muscles() {
        return option_muscles;
    }

    public void setOption_muscles(Set<Muscle> option_muscles) {
        this.option_muscles = option_muscles;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ExerciseDescription{" +
                "target_muscle=" + target_muscle +
                ", option_muscles=" + option_muscles +
                ", type='" + type + '\'' +
                ", complexity='" + complexity + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
