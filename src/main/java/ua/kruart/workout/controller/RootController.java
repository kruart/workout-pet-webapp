package ua.kruart.workout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Handles all requests begging with root path '/'

 * Created by kruart on 19.06.2017.
 */
@Controller
public class RootController {

    @GetMapping(value = "/")
    public String root() {
        return "index";
    }


    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
