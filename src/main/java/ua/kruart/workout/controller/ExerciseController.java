package ua.kruart.workout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.kruart.workout.service.ExerciseService;

/**
 * Handles exercise-related requests
 *
 * kruart on 04.06.2017.
 */
@Controller
@RequestMapping("/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService service;

    @GetMapping
    public ModelAndView getAll(@RequestParam("wid") Integer workoutId) {
        return new ModelAndView("exerciseList", "exerciseList", service.getAll(workoutId));
    }
}
