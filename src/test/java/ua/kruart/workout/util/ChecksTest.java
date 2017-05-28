package ua.kruart.workout.util;

import org.junit.Test;
import ua.kruart.workout.util.exception.InvalidParameterException;

/**
 * Verifies functionality of {@link Checks} class
 *
 * Created by kruart on 25.05.2017.
 */
public class ChecksTest {
    @Test
    public void testCheckParameterBoleanParamNoException() throws Exception {
        Checks.checkParameter(true, 1);
    }

    @Test
    public void testCheckParameterObjectParamNoException() throws Exception {
        Checks.checkParameter(new Object(), 1);
    }

    @Test(expected = InvalidParameterException.class)
    public void testCheckParameterBoleanParamGetException() throws Exception {
        Checks.checkParameter(false, 1);
    }

    @Test(expected = InvalidParameterException.class)
    public void testCheckParameterObjectParamGetException() throws Exception {
        Checks.checkParameter(null, 1);
    }

    @Test(expected = IllegalAccessException.class)
    public void testCheckPrivateConstructorGetException() throws Exception {
        Checks.class.newInstance();
    }

}