package ua.kruart.workout.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.kruart.workout.model.*;
import ua.kruart.workout.util.exception.InvalidParameterException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Verifies functionality of {@link ExerciseServiceImpl} class
 *
 * @author kruart on 03.06.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@Sql(scripts = "classpath:sql/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles("hsqldb")
public class ExerciseServiceImplTest {

    private static final int EXERCISE_CORRECT_ID = 7;
    private static final int EXERCISE_INCORRECT_ID = 3;
    private static int WORKOUT_CORRECT_ID = 2;
    private static int WORKOUT_INCORRECT_ID = 99;
    @Autowired
    private ExerciseService service;

    @Test
    public void testSave() {
        int sizeBeforeSave = service.getAll(WORKOUT_CORRECT_ID).size();
        Exercise testData = service.save(getTestData(), WORKOUT_CORRECT_ID);
        Exercise savedEx = service.get(testData.getId(), WORKOUT_CORRECT_ID);
        assertEquals(testData, savedEx);
        assertEquals(testData.getDescription().getName(), savedEx.getDescription().getName());
        assertNotEquals(sizeBeforeSave, service.getAll(WORKOUT_CORRECT_ID).size());
    }

    @Test
    public void testUpdateSuccess() {
        Exercise testData = service.get(EXERCISE_CORRECT_ID, WORKOUT_CORRECT_ID);
        testData.setComment("UPDATE");
        service.update(testData, WORKOUT_CORRECT_ID);
        assertEquals(testData.getComment(), service.get(testData.getId(), WORKOUT_CORRECT_ID).getComment());
    }

    @Test(expected = InvalidParameterException.class)
    public void testUpdateFailure() {
        Exercise testData = service.get(EXERCISE_CORRECT_ID, WORKOUT_CORRECT_ID);
        testData.setComment("UPDATE");
        service.update(testData, WORKOUT_INCORRECT_ID);
    }

    @Test
    public void testDeleteSuccess() throws Exception {
        assertEquals("Size of list: 2", 2, service.getAll(WORKOUT_CORRECT_ID).size());
        service.delete(EXERCISE_CORRECT_ID, WORKOUT_CORRECT_ID);
        assertEquals("Size of list after delete: 1", 1, service.getAll(WORKOUT_CORRECT_ID).size());
    }

    @Test(expected = InvalidParameterException.class)
    public void testDeleteFailure() throws Exception {
        service.delete(EXERCISE_INCORRECT_ID, WORKOUT_CORRECT_ID); //non-existing ID
    }

    @Test
    public void testGetSuccess() throws Exception {
        Exercise exercise = service.get(EXERCISE_CORRECT_ID, WORKOUT_CORRECT_ID);
        assertEquals((Integer) EXERCISE_CORRECT_ID, exercise.getId());
        assertEquals("Штанга на біцепс", exercise.getDescription().getName());
    }

    @Test(expected = InvalidParameterException.class)
    public void testGetFailure() throws Exception {
        Exercise exercise = service.get(EXERCISE_INCORRECT_ID, WORKOUT_CORRECT_ID);
    }

    @Test
    public void testGetAll() throws Exception {
        assertEquals(2, service.getAll(WORKOUT_CORRECT_ID).size());
    }

    public Exercise getTestData() {
        ExerciseConfiguration conf = new ExerciseConfiguration();
        Map<Muscle, String> muscles = new HashMap<>();
        muscles.put(Muscle.CHEST, "main");
        muscles.put(Muscle.SHOULDERS, "optional");
        muscles.put(Muscle.TRAPEZIUS, "optional");
        ExerciseDescription desc = new ExerciseDescription(muscles, "Basic", "simple", "Підтягування", "Do it right!");
        return new Exercise(null, conf, desc, null, "Важка тренька");
    }
}
