package ua.kruart.workout.controller.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ua.kruart.workout.util.exception.InvalidParameterException;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles all global exceptions.
 *
 * @author  kruart on 04.07.2017.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(InvalidParameterException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ModelAndView handleInvalidParamEx(HttpServletRequest req, InvalidParameterException e) {
        LOGGER.error("Exception at request " + req.getRequestURL());
        return new ModelAndView("exceptions/error").addObject("errMsg", e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllEx(HttpServletRequest req, Exception e) {
        LOGGER.error("Exception at request " + req.getRequestURL());
        return new ModelAndView("exceptions/error").addObject("errMsg", e.getMessage());
    }
}
