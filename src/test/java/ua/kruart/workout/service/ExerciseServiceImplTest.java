package ua.kruart.workout.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.kruart.workout.config.ApplicationConfig;
import ua.kruart.workout.config.DatabaseConfig;
import ua.kruart.workout.model.Exercise;
import ua.kruart.workout.model.ExerciseConfiguration;
import ua.kruart.workout.model.ExerciseDescription;
import ua.kruart.workout.model.Muscle;
import ua.kruart.workout.util.exception.InvalidParameterException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static ua.kruart.workout.UserTestData.USER;

/**
 * Verifies functionality of {@link ExerciseServiceImpl} class
 *
 * @author kruart on 03.06.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, DatabaseConfig.class})
@Sql(scripts = "classpath:sql/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles("hsqldb")
public class ExerciseServiceImplTest {

    private static final int EXERCISE_CORRECT_ID = 9;
    private static final int EXERCISE_INCORRECT_ID = 12;
    private static int WORKOUT_CORRECT_ID = 3;
    private static int WORKOUT_INCORRECT_ID = 99;
    @Autowired
    private ExerciseService service;

    @Test
    public void testSave() {
        int sizeBeforeSave = service.getAll(WORKOUT_CORRECT_ID, USER.getId()).size();
        Exercise testData = service.save(getTestData(), WORKOUT_CORRECT_ID, USER.getId());
        Exercise savedEx = service.get(testData.getId(), WORKOUT_CORRECT_ID, USER.getId());
        assertEquals(testData, savedEx);
        assertEquals(testData.getDescription().getName(), savedEx.getDescription().getName());
        assertNotEquals(sizeBeforeSave, service.getAll(WORKOUT_CORRECT_ID, USER.getId()).size());
    }

    @Test
    public void testUpdateSuccess() {
        Exercise testData = service.get(EXERCISE_CORRECT_ID, WORKOUT_CORRECT_ID, USER.getId());
        testData.setComment("UPDATE");
        service.update(testData, WORKOUT_CORRECT_ID, USER.getId());
        assertEquals(testData.getComment(), service.get(testData.getId(), WORKOUT_CORRECT_ID, USER.getId()).getComment());
    }

    @Test(expected = InvalidParameterException.class)
    public void testUpdateFailure() {
        Exercise testData = service.get(EXERCISE_CORRECT_ID, WORKOUT_CORRECT_ID, USER.getId());
        testData.setComment("UPDATE");
        service.update(testData, WORKOUT_INCORRECT_ID, USER.getId());
    }

    @Test
    public void testDeleteSuccess() throws Exception {
        assertEquals("Size of list: 3", 3, service.getAll(WORKOUT_CORRECT_ID, USER.getId()).size());
        service.delete(EXERCISE_CORRECT_ID, WORKOUT_CORRECT_ID, USER.getId());
        assertEquals("Size of list after delete: 2", 2, service.getAll(WORKOUT_CORRECT_ID, USER.getId()).size());
    }

    @Test(expected = InvalidParameterException.class)
    public void testDeleteFailure() throws Exception {
        service.delete(EXERCISE_INCORRECT_ID, WORKOUT_CORRECT_ID, USER.getId()); //non-existing ID
    }

    @Test
    public void testGetSuccess() throws Exception {
        Exercise exercise = service.get(EXERCISE_CORRECT_ID, WORKOUT_CORRECT_ID, USER.getId());
        assertEquals((Integer) EXERCISE_CORRECT_ID, exercise.getId());
        assertEquals("Розводка гантель", exercise.getDescription().getName());
    }

    @Test(expected = InvalidParameterException.class)
    public void testGetFailure() throws Exception {
        Exercise exercise = service.get(EXERCISE_INCORRECT_ID, WORKOUT_CORRECT_ID, USER.getId());
    }

    @Test
    public void testGetAll() throws Exception {
        assertEquals(3, service.getAll(WORKOUT_CORRECT_ID, USER.getId()).size());
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
