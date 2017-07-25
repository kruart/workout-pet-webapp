package ua.kruart.workout.controller.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Displays the last username after unsuccessful authentication.
 *
 * @author  kruart on 09.07.2017.
 */
public class UsernameRememberingAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(UsernameRememberingAuthenticationFailureHandler.class);

    private String redirectTarget;

    @Override
    public void onAuthenticationFailure(HttpServletRequest req,
                                        HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
        String username = req.getParameter("wUsername"); //find out what the username was in last login attempt
        LOGGER.info("FAILED LOGIN ATTEMPT WITH LOGIN " + username);

        resp.sendRedirect(redirectTarget + "?error&username=" + username);
    }

    public void setRedirectTarget(String redirectTarget) {
        this.redirectTarget = redirectTarget;
    }
}
