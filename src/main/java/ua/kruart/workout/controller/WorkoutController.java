package ua.kruart.workout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.kruart.workout.model.Workout;
import ua.kruart.workout.service.WorkoutService;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * Handles workout-related requests
 *
 * @author kruart on 28.05.2017.
 */
@Controller
@RequestMapping(value = "/workout")
public class WorkoutController {

    @Autowired
    private WorkoutService service;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllWorkouts(Model model) {
        model.addAttribute("workoutList", service.getAll());
        return "workoutList";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createWorkout() {
        return new ModelAndView("editWorkout").addObject("workoutModel", new Workout("", LocalDateTime.now().withSecond(0).withNano(0)));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView updateWorkout(@PathVariable Integer id) {
        return new ModelAndView("editWorkout").addObject("workoutModel", service.get(id));
    }

    @RequestMapping(value = "/saveChanges", method = RequestMethod.POST)
    public String createOrUpdate(@Valid @ModelAttribute("workoutModel") Workout workoutModel, BindingResult results) {
        if (results.hasErrors()) {
            return "editWorkout";
        }

        if (workoutModel.isNew()) {
            service.save(workoutModel);
        } else {
            service.update(workoutModel);
        }
        return "redirect:/workout";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String removeWorkout(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/workout";
    }
}
