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
        Checks.checkParameter(true, "test");
    }

    @Test
    public void testCheckParameterObjectParamNoException() throws Exception {
        Checks.checkParameter(new Object(), "test");
    }

    @Test(expected = InvalidParameterException.class)
    public void testCheckParameterBoleanParamGetException() throws Exception {
        Checks.checkParameter(false, "test");
    }

    @Test(expected = InvalidParameterException.class)
    public void testCheckParameterObjectParamGetException() throws Exception {
        Checks.checkParameter(null, "test");
    }

    @Test(expected = IllegalAccessException.class)
    public void testCheckPrivateConstructorGetException() throws Exception {
        Checks.class.newInstance();
    }

}