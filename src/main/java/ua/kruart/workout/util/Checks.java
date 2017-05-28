package ua.kruart.workout.util;


import ua.kruart.workout.util.exception.InvalidParameterException;

/**
 * Contains common checks routines
 *
 * @author kruart on 25.05.2017
 */
public final class Checks {

    private Checks() {
    }

    /**
     * Verifies that specified check passed or throws exception otherwise
     * @param check
     * @param id
     * @throws InvalidParameterException
     */
    public static void checkParameter(boolean check, int id) throws InvalidParameterException {
        if (!check) {
            throw new InvalidParameterException("Not found entity with id=" + id);
        }
    }

    /**
     * Verifies that specified check passed or throws exception otherwise
     * @param check
     * @param id
     * @throws InvalidParameterException
     */
    public static void checkParameter(Object check, int id) throws InvalidParameterException {
        checkParameter(check != null, id);
    }
}
