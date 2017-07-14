package ua.kruart.workout.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.kruart.workout.model.Approach;
import ua.kruart.workout.service.ApproachService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ua.kruart.workout.UserTestData.USER;

/**
 * Verifies functionality of {@link ApproachController} class
 *
 * Created by kruart on 25.06.2017.
 */
public class ApproachControllerTest extends AbstractControllerTest {

    @Autowired
    private ApproachService service;

    @Test
    public void getAllApproach() throws Exception{
        mockMvc.perform(get("/approach/all/exercise/10").with(userAuth(USER)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("approachList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/approachList.jsp"))
                .andExpect(model().attribute("approachList", service.getAll(10, USER.getId())));
    }

    @Test
    public void getAllApproachWithoutAuthentication() throws Exception{
        mockMvc.perform(get("/approach/all/exercise/10"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void testCreateApproach() throws Exception {
        mockMvc.perform(get("/approach/create/exercise/10").with(userAuth(USER)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("editApproach"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/editApproach.jsp"))
                .andExpect(model().attribute("approachModel", new Approach()));
    }

    @Test
    public void testUpdateApproach() throws Exception{
        mockMvc.perform(get("/approach/update/29/exercise/10").with(userAuth(USER)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("editApproach"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/editApproach.jsp"))
                .andExpect(model().attribute("approachModel", service.get(29, 10, USER.getId())));
    }

    @Test
    public void testCreateOrUpdateApproach() throws Exception {
        mockMvc.perform(post("/approach/saveChanges/exercise/10").with(userAuth(USER)).with(csrf())
                .param("id", "29")
                .param("eid", "10")
                .param("time", "23")
                .param("repeats", "8")
                .param("weight", "72.8")
                .param("distance", "0"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/approach/all/exercise/10"))
                .andExpect(redirectedUrl("/approach/all/exercise/10"));
    }

    @Test
    public void testRemoveApproach() throws Exception {
        mockMvc.perform(get("/approach/delete/29/exercise/10").with(userAuth(USER)))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/approach/all/exercise/10"))
                .andExpect(redirectedUrl("/approach/all/exercise/10"));
    }
}
