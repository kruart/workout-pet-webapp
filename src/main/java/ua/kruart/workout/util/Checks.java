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
     * @param message
     * @throws InvalidParameterException
     */
    public static void checkParameter(boolean check, String message) throws InvalidParameterException {
        if (!check) {
            throw new InvalidParameterException(message);
        }
    }

    /**
     * Verifies that specified check passed or throws exception otherwise
     * @param check
     * @param message
     * @throws InvalidParameterException
     */
    public static void checkParameter(Object check, String message) throws InvalidParameterException {
        checkParameter(check != null, message);
    }
}
