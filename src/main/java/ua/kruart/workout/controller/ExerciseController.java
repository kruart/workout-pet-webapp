package ua.kruart.workout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.kruart.workout.model.*;
import ua.kruart.workout.service.ExerciseService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
        return new ModelAndView("exerciseList", "exerciseList", service.getAll(workoutId)).addObject("wid", workoutId);
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
        Integer wid = Integer.parseInt(getDataFromReq("workoutId", req));
        Exercise exercise = getModel(req);

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

    private Exercise getModel(HttpServletRequest req) {
        String id = req.getParameter("id");

        return new Exercise(
                id.isEmpty() ? null : Integer.valueOf(id),
                new ExerciseConfiguration(
                        Boolean.valueOf(getDataFromReq("weightMeasure", req)),
                        Boolean.valueOf(getDataFromReq("timeMeasure", req)),
                        Boolean.valueOf(getDataFromReq("repeatMeasure", req)),
                        Boolean.valueOf(getDataFromReq("distanceMeasure", req))),
                new ExerciseDescription(
                        getMusclesMapFromReq(req),
                        getDataFromReq("type", req),
                        getDataFromReq("complexity", req),
                        getDataFromReq("name", req),
                        getDataFromReq("desc", req)),
                null,
                getDataFromReq("comment", req));
    }

    private String getDataFromReq(String param, HttpServletRequest req) {
        return req.getParameter(param);
    }

    private Map<Muscle, String> getMusclesMapFromReq(HttpServletRequest req) {
        Map<Muscle, String> muscles = new HashMap<>();
        muscles.put(Muscle.valueOf(req.getParameter("main")), "main");

        String[] lines = req.getParameterValues("optional");

        for(String line : lines){
            muscles.put(Muscle.valueOf(line), "optional");
        }

        return muscles;
    }


}
