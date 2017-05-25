package ua.kruart.workout.util.exception;

/**
 * Signals that incorrect parameter was passed to method/constructor
 *
 * @author kruart on 25.05.2017
 */
public class InvalidParameterException extends RuntimeException {

    private static final long serialVersionUID = 2985279278779405077L;

    public InvalidParameterException(String message) {
        super(message);
    }

    public InvalidParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
