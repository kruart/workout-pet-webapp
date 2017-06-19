package ua.kruart.workout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles all requests begging with root path '/'

 * Created by kruart on 19.06.2017.
 */
@Controller
public class RootController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }
}
