package ua.kruart.workout.model;

import java.util.List;

/**
 * Entity that describes exercise
 *
 * @author kruart on 14.05.2017.
 */
public class Exercise {

    /**
     * Unique entity identifier
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
     * Describes each approach of the exercise
     */
    private List<Approach> approaches;

    /**
     * Comment of the exercise
     */
    private String comment;

    public Exercise() {}

    public Exercise(int id, List<String> images, ExerciseConfiguration conf, ExerciseDescription description, List<Approach> approaches, String comment) {
        this.id = id;
        this.images = images;
        this.conf = conf;
        this.description = description;
        this.approaches = approaches;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public ExerciseConfiguration getConf() {
        return conf;
    }

    public void setConf(ExerciseConfiguration conf) {
        this.conf = conf;
    }

    public ExerciseDescription getDescription() {
        return description;
    }

    public void setDescription(ExerciseDescription description) {
        this.description = description;
    }

    public List<Approach> getApproaches() {
        return approaches;
    }

    public void setApproaches(List<Approach> approaches) {
        this.approaches = approaches;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exercise exercise = (Exercise) o;

        return id == exercise.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", images=" + images +
                ", conf=" + conf +
                ", description=" + description +
                ", approaches=" + approaches +
                ", comment='" + comment + '\'' +
                '}';
    }
}
