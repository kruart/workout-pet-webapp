package ua.kruart.workout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.kruart.workout.model.*;
import ua.kruart.workout.service.ExerciseService;
import ua.kruart.workout.util.Checks;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
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

    @GetMapping(value = "/all/workout/{wid}")
    public ModelAndView getAllExercise(@PathVariable("wid") Integer workoutId) {
        return new ModelAndView("exerciseList", "exerciseList", service.getAll(workoutId)).addObject("wid", workoutId);
    }

    @RequestMapping(value = "/delete/{id}/workout/{wid}", method = RequestMethod.GET)
    public String removeExercise(@PathVariable Integer id, @PathVariable("wid") Integer workoutId) {
        service.delete(id, workoutId);
        return "redirect:/exercise/all/workout/" + workoutId;
    }

    @RequestMapping(value = "/create/workout/{wid}", method = RequestMethod.GET)
    public ModelAndView createExercise(@PathVariable("wid") Integer workoutId) {
        return new ModelAndView("editExercise")
                .addObject("exerciseModel", new Exercise(null, new ExerciseConfiguration(), new ExerciseDescription(), null, null, ""))
                .addObject("wid", workoutId);
    }

    @RequestMapping(value = "/update/{id}/workout/{wid}", method = RequestMethod.GET)
    public ModelAndView updateExercise(@PathVariable Integer id, @PathVariable("wid") Integer workoutId) {
        return new ModelAndView("editExercise")
                .addObject("exerciseModel", service.get(id, workoutId)).addObject("wid", workoutId);
    }

    @RequestMapping(value = "/saveChanges/workout/{wid}", method = RequestMethod.POST)
    public String createOrUpdate(@PathVariable("wid") Integer workoutId, @Valid @ModelAttribute("exerciseModel") Exercise exerciseModel,
                                 BindingResult result, ModelMap map, HttpServletRequest req) {
        if (result.hasErrors()) {
            map.addAttribute("wid", workoutId);
            return "editExercise";
        }

        exerciseModel.getDescription().setMuscles(getMusclesMapFromReq(req));

        if (exerciseModel.isNew()) {
            service.save(exerciseModel, workoutId);
        } else {
            service.update(exerciseModel, workoutId);
        }
        return "redirect:/exercise/all/workout/" + workoutId;
    }

    private Map<Muscle, String> getMusclesMapFromReq(HttpServletRequest req) {
        Map<Muscle, String> muscles = new HashMap<>();

        if (!Checks.paramIsEmpty(req.getParameter("main"))) {
            muscles.put(Muscle.valueOf(req.getParameter("main")), "main");
        }

        Arrays.stream(
                req.getParameterValues("optional"))
                .filter((e) -> !Checks.paramIsEmpty(e))
                .forEach((line) -> muscles.put(Muscle.valueOf(line), "optional"));
        return muscles;
    }


}
