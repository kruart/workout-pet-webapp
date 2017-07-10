package ua.kruart.workout.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kruart.workout.util.exception.InvalidParameterException;

/**
 * Contains common checks routines
 *
 * @author kruart on 25.05.2017
 */
public final class Checks {
    private static final Logger LOGGER = LoggerFactory.getLogger(Checks.class);

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
            LOGGER.info("Not found entity with id=" + id);
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

    /**
     * Verifies if specified string is null or empty
     * @param str
     */
    public static boolean paramIsEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * Verifies that specified check passed or throws exception otherwise
     * @param check
     * @param str
     * @throws InvalidParameterException
     */
    public static <T> T checkParameter(T check, String str) throws InvalidParameterException {
        if (check == null) {
            LOGGER.info("Not found " + str);
            throw new InvalidParameterException("Not found " + str);
        }
        return check;
    }
}
