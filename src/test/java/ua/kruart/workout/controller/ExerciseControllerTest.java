package ua.kruart.workout.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.kruart.workout.model.Exercise;
import ua.kruart.workout.service.ExerciseService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Verifies functionality of {@link ExerciseController} class
 *
 * Created by kruart on 25.06.2017.
 */
public class ExerciseControllerTest extends AbstractControllerTest {

    @Autowired
    private ExerciseService service;

    @Test
    public void getAllExercise() throws Exception{
        mockMvc.perform(get("/exercise/all/workout/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("exerciseList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/exerciseList.jsp"))
                .andExpect(model().attribute("exerciseList", service.getAll(1)));
    }

    @Test
    public void testCreateExercise() throws Exception {
        mockMvc.perform(get("/exercise/create/workout/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("editExercise"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/editExercise.jsp"))
                .andExpect(model().attribute("exerciseModel", new Exercise()));
    }

    @Test
    public void testUpdateExercise() throws Exception{
        mockMvc.perform(get("/exercise/update/3/workout/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("editExercise"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/editExercise.jsp"))
                .andExpect(model().attribute("exerciseModel", service.get(3, 1)));
    }

    @Test
    public void testCreateOrUpdateExercise() throws Exception {
        mockMvc.perform(post("/exercise/saveChanges/workout/1").param("id", "3")
                .param("wid", "1")
                .param("conf.weightMeasure", "true")
                .param("conf.timeMeasure", "false")
                .param("conf.repeatMeasure", "true")
                .param("conf.distanceMeasure", "false")
                .param("description.type", "Basic")
                .param("description.complexity", "medium")
                .param("description.name", "Push-ups")
                .param("description.description", "bla-bla")
                .param("comment", "very hard")
                .param("main", "CHEST")
                .param("optional", "SHOULDERS"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/exercise/all/workout/1"))
                .andExpect(redirectedUrl("/exercise/all/workout/1"));
    }

    @Test
    public void testRemoveExercise() throws Exception {
        mockMvc.perform(get("/exercise/delete/3/workout/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/exercise/all/workout/1"))
                .andExpect(redirectedUrl("/exercise/all/workout/1"));
    }
}
