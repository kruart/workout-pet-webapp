package ua.kruart.workout.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.kruart.workout.model.Approach;
import ua.kruart.workout.util.exception.InvalidParameterException;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static ua.kruart.workout.UserTestData.USER;

/**
 * Verifies functionality of {@link ua.kruart.workout.service.ApproachServiceImpl} class
 * <p>
 * Created by kruart on 23.06.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@Sql(scripts = "classpath:sql/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles("hsqldb")
public class ApproachServiceImplTest {

    @Autowired
    private ApproachService service;

    private static final int APPROACH_CORRECT_ID = 25;
    private static final int EXERCISE_CORRECT_ID = 9;
    private static final int EXERCISE_INCORRECT_ID = 99;

    @Test
    public void testSaveApproach() throws Exception {
        Approach testData = service.save(getTestData(), EXERCISE_CORRECT_ID, USER.getId());
        Approach savedApproach = service.get(testData.getId(), EXERCISE_CORRECT_ID, USER.getId());

        assertEquals(testData, savedApproach);
        assertEquals(testData.getWeight(), savedApproach.getWeight(), 0.01);
    }

    @Test
    public void testUpdateApproachSuccess() throws Exception {
        Approach approachById = service.get(APPROACH_CORRECT_ID, EXERCISE_CORRECT_ID, USER.getId());
        approachById.setRepeats(111);
        service.update(approachById, EXERCISE_CORRECT_ID, USER.getId());
        assertEquals(approachById.getRepeats(), service.get(approachById.getId(), EXERCISE_CORRECT_ID, USER.getId()).getRepeats());
    }

    @Test(expected = InvalidParameterException.class)
    public void testUpdateApproachFailure() throws Exception {
        Approach approachById = service.get(APPROACH_CORRECT_ID, EXERCISE_CORRECT_ID, USER.getId());
        approachById.setRepeats(111);
        service.update(approachById, EXERCISE_INCORRECT_ID, USER.getId());
    }

    @Test
    public void testDeleteApproachSuccess() throws Exception {
        assertEquals("Size of approach list: 4", 4, service.getAll(EXERCISE_CORRECT_ID, USER.getId()).size());
        service.delete(APPROACH_CORRECT_ID, EXERCISE_CORRECT_ID, USER.getId());
        assertEquals("Size of approach list after delete: 3", 3, service.getAll(EXERCISE_CORRECT_ID, USER.getId()).size());
    }

    @Test(expected = InvalidParameterException.class)
    public void testDeleteApproachFailure() throws Exception {
        service.delete(APPROACH_CORRECT_ID, EXERCISE_INCORRECT_ID, USER.getId()); //non-existing ID
    }

    @Test
    public void testGetApproachByIdsSuccess() throws Exception {
        Approach approachById = service.get(APPROACH_CORRECT_ID, EXERCISE_CORRECT_ID, USER.getId());
        assertEquals(15.2, approachById.getWeight(), 0.01);
        assertEquals(Integer.valueOf(8), approachById.getRepeats());
    }

    @Test(expected = InvalidParameterException.class)
    public void testGetApproachByIdsFailure() throws Exception {
        Approach approachById = service.get(APPROACH_CORRECT_ID, EXERCISE_INCORRECT_ID, USER.getId());
    }

    @Test
    public void testGetAllApproaches() throws Exception {
        List<Approach> approaches = service.getAll(EXERCISE_CORRECT_ID, USER.getId());
        assertEquals(4, approaches.size());
    }


    public Approach getTestData() {
        return new Approach(null, 5, 99.9f, 0.0f, 0);
    }

}