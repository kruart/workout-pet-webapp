package ua.kruart.workout.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

/**
 * Contains utility functions for work with user password
 *
 * @author kruart on 11.07.2017.
 */
public class PasswordUtil {

    private static BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Encode the raw password.
     */
    public static String encode(String newPassword) {
        if (StringUtils.isEmpty(newPassword)) {
            return null;
        }
        return bCryptPasswordEncoder.encode(newPassword);
    }

    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        PasswordUtil.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}
