package ua.kruart.workout.model;

/**
 * Type of the muscle that we support in the application
 *
 * @author kruart, on 13.05.2017
 */
public enum Muscle {
    ABS("muscle.abs"),                  //пресс
    BICEPS("muscle.biceps"),
    CALVES("muscle.calves"),            //икры
    CHEST("muscle.chest"),
    FOREARMS("muscle.forearms"),        //предплечья
    QUADRICEPS("muscle.quadriceps"),
    GLUTES("muscle.glutes"),            //ягодичные мышцы
    HAMSTRINGS("muscle.hamstrings"),    //бедра
    LATISSIMUS("muscle.latissimus"),    //широчайшие
    LOWER_BACK("muscle.lower_back"),    //нижняя часть спины
    MIDDLE_BACK("muscle.middle_back"),  //средняя часть спины
    NECK("muscle.neck"),
    SHOULDERS("muscle.shoulders"),
    TRAPEZIUS("muscle.trapezius"),
    TRICEPS("muscle.triceps");

    private final String muscle;

    private Muscle(final String muscle) {
        this.muscle = muscle;
    }

    public String getMuscle() {
        return muscle;
    }
    @Override
    public String toString() {
        return muscle;
    }
}
