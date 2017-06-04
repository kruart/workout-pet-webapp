package ua.kruart.workout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.kruart.workout.model.Exercise;
import ua.kruart.workout.model.ExerciseConfiguration;
import ua.kruart.workout.model.ExerciseDescription;
import ua.kruart.workout.model.Workout;
import ua.kruart.workout.service.ExerciseService;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createExercise(@RequestParam("wid") Integer workoutId) {
        Workout workout = new Workout();
        workout.setId(workoutId);
        return new ModelAndView("editExercise")
                .addObject("new_or_edit_model", new Exercise(null, new ExerciseConfiguration(), new ExerciseDescription(), null, workout, ""));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView updateExercise(@PathVariable Integer id, @RequestParam("wid") Integer workoutId) {
        return new ModelAndView("editExercise")
                .addObject("new_or_edit_model", service.get(id, workoutId));
    }

    @RequestMapping(value = "/saveChanges", method = RequestMethod.POST)
    public String createOrUpdate(HttpServletRequest req) {
        // TODO: try to create converter String to Map<Muscle, String> and make refactoring
        String id = req.getParameter("id");
        Integer wid = Integer.parseInt(req.getParameter("workoutId"));
        ExerciseConfiguration conf = new ExerciseConfiguration(
                Boolean.valueOf(getFromReq("weightMeasure", req)),
                Boolean.valueOf(getFromReq("timeMeasure", req)),
                Boolean.valueOf(getFromReq("repeatMeasure", req)),
                Boolean.valueOf(getFromReq("distanceMeasure", req))
        );

        ExerciseDescription desc = new ExerciseDescription(
                null,
                getFromReq("type", req),
                getFromReq("complexity", req),
                getFromReq("name", req),
                getFromReq("desc", req)
        );


        Exercise exercise = new Exercise(
                id.isEmpty() ? null : Integer.valueOf(id),
                conf,
                desc,
                null,
                getFromReq("comment", req));

        if (exercise.isNew()) {
            service.save(exercise, wid);
        } else {
            service.update(exercise, wid);
        }
        return "redirect:/exercise?wid=" + wid;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String removeExerciseById(@PathVariable Integer id, @RequestParam("wid") Integer wid) {
        service.delete(id, wid);
        return "redirect:/exercise?wid=" + wid;
    }

    private String getFromReq(String param, HttpServletRequest req) {
        return req.getParameter(param);
    }
}
