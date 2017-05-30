package ua.kruart.workout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.kruart.workout.service.WorkoutService;

/**
 *
 * @author kruart on 28.05.2017.
 */
@Controller
@RequestMapping(value = "/workout")
public class WorkoutController {

    @Autowired
    private WorkoutService service;

    @RequestMapping(method = RequestMethod.GET)
    public String workoutList(Model model) {
        model.addAttribute("workoutList", service.getAll());
        return "workoutList";
    }
}
