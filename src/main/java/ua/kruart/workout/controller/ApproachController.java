package ua.kruart.workout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.kruart.workout.model.Approach;
import ua.kruart.workout.service.ApproachService;

import javax.validation.Valid;
import java.util.List;

/**
 * Handles approach-related requests
 *
 * Created by kruart on 24.06.2017.
 */
@Controller
@RequestMapping("/approach")
public class ApproachController {

    @Autowired
    private ApproachService service;

    @GetMapping("/all/exercise/{eid}")
    public ModelAndView getAllApproaches(@PathVariable("eid") Integer exerciseId) {
        List<Approach> approaches = service.getAll(exerciseId);
        return new ModelAndView("approachList", "approachList", approaches).addObject("eid", exerciseId);
    }

    @GetMapping("/create/exercise/{eid}")
    public ModelAndView createApproach(@PathVariable("eid") Integer exerciseId) {
        return new ModelAndView("editApproach", "approachModel", new Approach(null, 0, 0.0f, 0.0f, 0)).addObject("eid", exerciseId);
    }

    @GetMapping("/update/{id}/exercise/{eid}")
    public ModelAndView updateApproach(@PathVariable Integer id, @PathVariable("eid") Integer exerciseId) {
        return new ModelAndView("editApproach", "approachModel", service.get(id, exerciseId)).addObject("eid", exerciseId);
    }

    @PostMapping("/saveChanges/exercise/{eid}")
    public String createOrUpdate(@PathVariable(name = "eid") Integer exerciseId,
                                 @Valid @ModelAttribute("approachModel") Approach approach, BindingResult result, ModelMap map) {
        if (result.hasErrors()) {
            map.addAttribute("eid", exerciseId);
            return "editApproach";
        }

        if(approach.isNew()) {
            service.save(approach, exerciseId);
        } else {
            service.update(approach, exerciseId);
        }

        return "redirect:/approach/all/exercise/" + exerciseId;
    }

    @GetMapping("/delete/{id}/exercise/{eid}")
    public String removeApproach(@PathVariable Integer id, @PathVariable("eid") Integer exerciseId) {
        service.delete(id, exerciseId);
        return "redirect:/approach/all/exercise/" + exerciseId;
    }
}

