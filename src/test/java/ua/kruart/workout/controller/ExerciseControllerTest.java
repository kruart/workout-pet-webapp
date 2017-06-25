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
        mockMvc.perform(get("/exercise").param("wid", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("exerciseList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/exerciseList.jsp"))
                .andExpect(model().attribute("exerciseList", service.getAll(1)));
    }

    @Test
    public void testCreateExercise() throws Exception {
        mockMvc.perform(get("/exercise/create").param("wid", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("editExercise"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/editExercise.jsp"))
                .andExpect(model().attribute("exerciseModel", new Exercise()));
    }

    @Test
    public void testUpdateExercise() throws Exception{
        mockMvc.perform(get("/exercise/update/3").param("wid", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("editExercise"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/editExercise.jsp"))
                .andExpect(model().attribute("exerciseModel", service.get(3, 1)));
    }

    @Test
    public void testCreateOrUpdateExercise() throws Exception {
        mockMvc.perform(post("/exercise/saveChanges").param("id", "3")
                .param("workoutId", "1")
                .param("weightMeasure", "true")
                .param("timeMeasure", "false")
                .param("repeatMeasure", "true")
                .param("distanceMeasure", "false")
                .param("type", "Basic")
                .param("complexity", "medium")
                .param("name", "Push-ups")
                .param("desc", "bla-bla")
                .param("comment", "very hard")
                .param("main", "CHEST")
                .param("optional", "SHOULDERS"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/exercise?wid=1"))
                .andExpect(redirectedUrl("/exercise?wid=1"));
    }

    @Test
    public void testRemoveExercise() throws Exception {
        mockMvc.perform(get("/exercise/delete/3").param("wid", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/exercise?wid=1"))
                .andExpect(redirectedUrl("/exercise?wid=1"));
    }
}
