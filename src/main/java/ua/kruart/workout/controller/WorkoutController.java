package ua.kruart.workout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author kruart on 28.05.2017.
 */
@Controller
@RequestMapping(value = "/workout")
public class WorkoutController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "hello";
    }
}
