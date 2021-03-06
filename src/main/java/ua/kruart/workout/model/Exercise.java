package ua.kruart.workout.model;

import ua.kruart.workout.model.base.BaseEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Entity that describes exercise
 *
 * @author kruart on 14.05.2017.
 */
@NamedQueries({
        @NamedQuery(name = "Exercise.findById",
                query = "SELECT e from Exercise e inner join e.workout w ON w.id=:workoutId inner join w.user u ON u.id=:userId where e.id=:id"),
        @NamedQuery(name = "Exercise.delete", query = "DELETE FROM Exercise e WHERE e.id=:id AND e.workout.id=:workoutId"),
        @NamedQuery(name = "Exercise.findAll",
                query = "SELECT e from Exercise e inner join e.workout w ON w.id=:workoutId inner join w.user u ON u.id=:userId")
})
@Table(name = "tbl_exercise")
@Entity
public class Exercise extends BaseEntity {

    /**
     * Configuration of exercise measures(weight, distance, repeats, time)
     */
    @Embedded
    private ExerciseConfiguration conf;

    /**
     * Description of the exercise
     */
    @Embedded
    @Valid
    private ExerciseDescription description;

    /**
     * Describes each approach of the exercise
     */
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "exercise")
    @OrderBy    //ordering by primary key is assumed
    private List<Approach> approaches;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    /**
     * Comment of the exercise
     */
    @Column(name = "comment")
    @Size(max = 256)
    private String comment;

    public Exercise() {}

    public Exercise(Integer id, ExerciseConfiguration conf, ExerciseDescription description, List<Approach> approaches, String comment) {
        super(id);
        this.conf = conf;
        this.description = description;
        this.approaches = approaches;
        this.comment = comment;
    }

    public Exercise(Integer id, ExerciseConfiguration conf, ExerciseDescription description, List<Approach> approaches, Workout workout, String comment) {
        this(id, conf, description, approaches, comment);
        this.workout = workout;

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

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "conf=" + conf +
                ", description=" + description +
                ", approaches=" + approaches +
                ", comment='" + comment + '\'' +
                '}';
    }
}
