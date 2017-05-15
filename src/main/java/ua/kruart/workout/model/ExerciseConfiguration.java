package ua.kruart.workout.model;

/**
 * Determines what configuration need for the {@link Exercise} object.
 *
 * @author kruart on 14.05.2017.
 */
public class ExerciseConfiguration {

    private boolean weightMeaseare;
    private boolean timeMeasure;
    private boolean repeatMeasure;
    private boolean distanceMeasure;

    public ExerciseConfiguration() {
        weightMeaseare = true;
        repeatMeasure = true;
        timeMeasure = false;
        distanceMeasure = false;
    }

    public ExerciseConfiguration(boolean weightMeaseare, boolean timeMeasure, boolean repeatMeasure, boolean distanceMeasure) {
        this.weightMeaseare = weightMeaseare;
        this.timeMeasure = timeMeasure;
        this.repeatMeasure = repeatMeasure;
        this.distanceMeasure = distanceMeasure;
    }

    public boolean isWeightMeaseare() {
        return weightMeaseare;
    }

    public void setWeightMeaseare(boolean weightMeaseare) {
        this.weightMeaseare = weightMeaseare;
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
                "weightMeaseare=" + weightMeaseare +
                ", timeMeasure=" + timeMeasure +
                ", repeatMeasure=" + repeatMeasure +
                ", distanceMeasure=" + distanceMeasure +
                '}';
    }
}
