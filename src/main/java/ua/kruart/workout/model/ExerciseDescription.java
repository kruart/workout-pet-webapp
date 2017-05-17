package ua.kruart.workout.model;

import javax.persistence.*;
import java.util.Map;

/**
 * Contains description of the {@link Exercise} object
 *
 * @author kruart on 14.05.2017.
 */
@Embeddable
public class ExerciseDescription {

    /**
     * Target and additional muscles that are involved in the exercise
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tbl_muscles", joinColumns = @JoinColumn(name = "exercise_id"))
    @MapKeyColumn(name = "name", length = 50, nullable = false)
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "working_muscle")
    private Map<Muscle, String> muscles;

    /**
     * Type of complexity: Basic or Isolating
     */
    @Column(name = "type", nullable = false)
    private String type;

    /**
     * Simple, Middle, Hard
     */
    @Column(name = "complexity", nullable = false)
    private String complexity;

    /**
     * Name of the exercise
     */
    @Column(name = "muscle_name", nullable = false)
    private String name;

    /**
     * Description of the exercise. How to do it correctly
     */
    @Column(name = "description")
    private String description;

    public ExerciseDescription() {}

    public ExerciseDescription(Map<Muscle, String> muscles, String type, String complexity, String name, String description) {
        this.muscles = muscles;
        this.type = type;
        this.complexity = complexity;
        this.name = name;
        this.description = description;
    }

    public Map<Muscle, String> getMuscles() {
        return muscles;
    }

    public void setMuscles(Map<Muscle, String> muscles) {
        this.muscles = muscles;
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
                "muscles=" + muscles +
                ", type='" + type + '\'' +
                ", complexity='" + complexity + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
