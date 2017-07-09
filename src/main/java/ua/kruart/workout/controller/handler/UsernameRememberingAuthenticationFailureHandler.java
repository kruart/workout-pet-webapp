package ua.kruart.workout.controller.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
    /**
     * It's springsecurity filter. We use it to get name of the username request parameter and it help us to get rid of hard-coding.
     * So, we can change name of the username request parameter in spring-security config or login.jsp
     * and any part of our code is not related to this name param.
     * It help us to write abstract code.
     */
    @Autowired
    private UsernamePasswordAuthenticationFilter filter;

    private String redirectTarget;

    @Override
    public void onAuthenticationFailure(HttpServletRequest req,
                                        HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
        String usernameParameter = filter.getUsernameParameter();//get from filter username param  which we coded in spring-security(wUsername)
        String username = req.getParameter(usernameParameter); //and find out what the username was in last login attempt
        LOGGER.info("FAILED LOGIN ATTEMPT WITH LOGIN " + username);

        //then do redirect
        resp.sendRedirect(redirectTarget + "?error&username=" + username);
    }

    public void setRedirectTarget(String redirectTarget) {
        this.redirectTarget = redirectTarget;
    }
}
