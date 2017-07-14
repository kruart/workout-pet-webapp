package ua.kruart.workout.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.kruart.workout.model.Workout;
import ua.kruart.workout.service.WorkoutService;

import java.time.LocalDateTime;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ua.kruart.workout.UserTestData.USER;

/**
 * Verifies functionality of {@link WorkoutController} class
 *
 * Created by kruart on 25.06.2017.
 */
public class WorkoutControllerTest extends AbstractControllerTest {

    @Autowired
    private WorkoutService service;

    @Test
    public void testGetAllWorkouts() throws Exception {
        mockMvc.perform(get("/workout").with(userAuth(USER)))
                .andDo(print())
//                .andExpect(status().isOk())
                .andExpect(view().name("workoutList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/workoutList.jsp"));
    }

    @Test
    public void testGetAllWorkoutsWithoutAuthentication() throws Exception{
        mockMvc.perform(get("/workout"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void testCreateWorkout() throws Exception {
        mockMvc.perform(get("/workout/create").with(userAuth(USER)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("editWorkout"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/editWorkout.jsp"))
                .andExpect(model().attribute("workoutModel", new Workout("", LocalDateTime.now()))); //equals only ids
    }

    @Test
    public void testUpdateWorkout() throws Exception {
        mockMvc.perform(get("/workout/update/3").with(userAuth(USER)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("editWorkout"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/editWorkout.jsp"))
                .andExpect(model().attribute("workoutModel", service.get(3, USER.getId())))  //equals only ids
                .andReturn();
    }

    @Test
    public void testCreateOrUpdateWorkout() throws Exception {

        mockMvc.perform(post("/workout/saveChanges").with(userAuth(USER)).with(csrf())
                .param("id", "3")
                .param("name", "ROUGH TRAINY CHEST")
                .param("startWorkout", "2017-05-19T12:09:49")
                .param("endWorkout", "2017-05-19T12:09:49"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/workout"))
                .andExpect(redirectedUrl("/workout"));
    }

    @Test
    public void testRemoveWorkout() throws Exception {
        mockMvc.perform(get("/workout/delete/3").with(userAuth(USER))
                .param("id", "3"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/workout"))
                .andExpect(redirectedUrl("/workout"));
    }
}
