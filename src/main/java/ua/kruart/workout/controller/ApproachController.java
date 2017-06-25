package ua.kruart.workout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.kruart.workout.model.Approach;
import ua.kruart.workout.service.ApproachService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
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

    @GetMapping()
    public ModelAndView getAllApproaches(@RequestParam("eid") Integer exerciseId) {
        List<Approach> approaches = service.getAll(exerciseId);
        return new ModelAndView("approachList", "approachList", approaches).addObject("eid", exerciseId);
    }

    @GetMapping("create")
    public ModelAndView createApproach(@RequestParam("eid") Integer exerciseId) {
        return new ModelAndView("editApproach", "approachModel", new Approach(null, 0, 0, 0, null)).addObject("eid", exerciseId);
    }

    @GetMapping("update/{id}")
    public ModelAndView updateApproach(@PathVariable Integer id, @RequestParam("eid") Integer exerciseId) {
        return new ModelAndView("editApproach", "approachModel", service.get(id, exerciseId)).addObject("eid", exerciseId);
    }

    @PostMapping("saveChanges")
    public String createOrUpdate(HttpServletRequest req) {
        Integer exerciseId = Integer.parseInt(req.getParameter("eid"));
        String id = req.getParameter("id");
        String time = req.getParameter("time");
        Approach approach = new Approach(
                id.isEmpty() ? null : Integer.parseInt(id),
                Integer.parseInt(req.getParameter("repeats")),
                Float.parseFloat(req.getParameter("weight")),
                Float.parseFloat(req.getParameter("distance")),
                time.isEmpty() ? null : LocalTime.parse(time));

        if(approach.isNew()) {
            service.save(approach, exerciseId);
        } else {
            service.update(approach, exerciseId);
        }

        return "redirect:/approach?eid=" + exerciseId;
    }

    @GetMapping("delete/{id}")
    public String removeApproach(@PathVariable Integer id, @RequestParam("eid") Integer exerciseId) {
        service.delete(id, exerciseId);
        return "redirect:/approach?eid=" + exerciseId;
    }
}
