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

import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
    private static final int EXERCISE_CORRECT_ID = 7;
    private static final int EXERCISE_INCORRECT_ID = 99;

    @Test
    public void testSaveApproach() throws Exception {
        Approach testData = service.save(getTestData(), EXERCISE_CORRECT_ID);
        Approach savedApproach = service.get(testData.getId(), EXERCISE_CORRECT_ID);

        assertEquals(testData, savedApproach);
        assertEquals(testData.getWeight(), savedApproach.getWeight(), 0.01);
    }

    @Test
    public void testUpdateApproachSuccess() throws Exception {
        Approach approachById = service.get(APPROACH_CORRECT_ID, EXERCISE_CORRECT_ID);
        approachById.setRepeats(111);
        service.update(approachById, EXERCISE_CORRECT_ID);
        assertEquals(approachById.getRepeats(), service.get(approachById.getId(), EXERCISE_CORRECT_ID).getRepeats());
    }

    @Test(expected = InvalidParameterException.class)
    public void testUpdateApproachFailure() throws Exception {
        Approach approachById = service.get(APPROACH_CORRECT_ID, EXERCISE_CORRECT_ID);
        approachById.setRepeats(111);
        service.update(approachById, EXERCISE_INCORRECT_ID);
    }

    @Test
    public void testDeleteApproachSuccess() throws Exception {
        assertEquals("Size of approach list: 4", 4, service.getAll(EXERCISE_CORRECT_ID).size());
        service.delete(APPROACH_CORRECT_ID, EXERCISE_CORRECT_ID);
        assertEquals("Size of approach list after delete: 3", 3, service.getAll(EXERCISE_CORRECT_ID).size());
    }

    @Test(expected = InvalidParameterException.class)
    public void testDeleteApproachFailure() throws Exception {
        service.delete(APPROACH_CORRECT_ID, EXERCISE_INCORRECT_ID); //non-existing ID
    }

    @Test
    public void testGetApproachByIdsSuccess() throws Exception {
        Approach approachById = service.get(APPROACH_CORRECT_ID, EXERCISE_CORRECT_ID);
        assertEquals(45.7, approachById.getWeight(), 0.01);
        assertEquals(8, approachById.getRepeats());
    }

    @Test(expected = InvalidParameterException.class)
    public void testGetApproachByIdsFailure() throws Exception {
        Approach approachById = service.get(APPROACH_CORRECT_ID, EXERCISE_INCORRECT_ID);
    }

    @Test
    public void testGetAllApproaches() throws Exception {
        List<Approach> approaches = service.getAll(EXERCISE_CORRECT_ID);
        assertEquals(4, approaches.size());
    }


    public Approach getTestData() {
        return new Approach(null, 5, 99.9f, 0, LocalTime.now());
    }

}