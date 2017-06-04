package ua.kruart.workout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.kruart.workout.model.Workout;
import ua.kruart.workout.service.WorkoutService;

import javax.servlet.http.HttpServletRequest;
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
    public String workoutList(Model model) {
        model.addAttribute("workoutList", service.getAll());
        return "workoutList";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createWorkout() {
        return new ModelAndView("editWorkout").addObject("new_or_edit_model", new Workout("", LocalDateTime.now()));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView updateWorkout(@PathVariable Integer id) {
        return new ModelAndView("editWorkout").addObject("new_or_edit_model", service.get(id));
    }

    @RequestMapping(value = "/saveChanges", method = RequestMethod.POST)
    public String createOrUpdate(HttpServletRequest req) {
        String id = req.getParameter("id");
        Workout workout = new Workout(
                id.isEmpty() ? null : Integer.valueOf(id),
                req.getParameter("name"),
                null,
                LocalDateTime.parse(req.getParameter("startWorkout")),
                LocalDateTime.parse(req.getParameter("endWorkout")));

        if (workout.isNew()) {
            service.save(workout);
        } else {
            service.update(workout);
        }
        return "redirect:/workout";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String removeWorkoutById(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/workout";
    }
}
