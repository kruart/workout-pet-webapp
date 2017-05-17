package ua.kruart.workout.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Determines what configuration need for the {@link Exercise} object.
 *
 * @author kruart on 14.05.2017.
 */
@Embeddable
public class ExerciseConfiguration {

    @Column(name = "weightMeasure")
    private boolean weightMeasure;

    @Column(name = "timeMeasure")
    private boolean timeMeasure;

    @Column(name = "repeatMeasure")
    private boolean repeatMeasure;

    @Column(name = "distanceMeasure")
    private boolean distanceMeasure;

    public ExerciseConfiguration() {
        weightMeasure = true;
        repeatMeasure = true;
        timeMeasure = false;
        distanceMeasure = false;
    }

    public ExerciseConfiguration(boolean weightMeasure, boolean timeMeasure, boolean repeatMeasure, boolean distanceMeasure) {
        this.weightMeasure = weightMeasure;
        this.timeMeasure = timeMeasure;
        this.repeatMeasure = repeatMeasure;
        this.distanceMeasure = distanceMeasure;
    }

    public boolean isWeightMeasure() {
        return weightMeasure;
    }

    public void setWeightMeasure(boolean weightMeasure) {
        this.weightMeasure = weightMeasure;
    }

    public boolean isTimeMeasure() {
        return timeMeasure;
    }

    public void setTimeMeasure(boolean timeMeasure) {
        this.timeMeasure = timeMeasure;
    }

    public boolean isRepeatMeasure() {
        return repeatMeasure;
    }

    public void setRepeatMeasure(boolean repeatMeasure) {
        this.repeatMeasure = repeatMeasure;
    }

    public boolean isDistanceMeasure() {
        return distanceMeasure;
    }

    public void setDistanceMeasure(boolean distanceMeasure) {
        this.distanceMeasure = distanceMeasure;
    }

    @Override
    public String toString() {
        return "ExerciseConfiguration{" +
                "weightMeasure=" + weightMeasure +
                ", timeMeasure=" + timeMeasure +
                ", repeatMeasure=" + repeatMeasure +
                ", distanceMeasure=" + distanceMeasure +
                '}';
    }
}
