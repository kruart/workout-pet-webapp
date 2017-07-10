package ua.kruart.workout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import ua.kruart.workout.model.Role;
import ua.kruart.workout.model.User;
import ua.kruart.workout.service.UserService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashSet;

/**
 * Handles all requests begging with root path '/'

 * Created by kruart on 19.06.2017.
 */
@Controller
public class RootController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/")
    public String root() {
        return "index";
    }


    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/register")
    public ModelAndView register() {
        return new ModelAndView("profileOrRegister")
                .addObject("userModel", new User())
                .addObject("type", "register");
    }

    @PostMapping(value = "/register")
    public String saveRegister(@ModelAttribute("userModel") @Valid User user, BindingResult result, SessionStatus status, ModelMap model) {
        if (!result.hasErrors()) {
            try {
                service.save(prepareForSave(user));
                status.setComplete();
                return "redirect:/login?afterR";
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("email", "error.user", "user with this email already present in application");
            }
        }
        //if error exist
        model.put("type", "register");
        return "profileOrRegister";
    }

    private User prepareForSave(User user) {
        user.setId(null);
        user.setRoles(new HashSet<>(Collections.singletonList(Role.ROLE_USER)));
        return user;
    }
}
