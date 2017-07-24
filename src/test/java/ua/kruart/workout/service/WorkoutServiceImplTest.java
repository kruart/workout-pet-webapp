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
import ua.kruart.workout.model.Workout;
import ua.kruart.workout.util.exception.InvalidParameterException;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * Verifies functionality of {@link WorkoutServiceImpl} class
 *
 * @author kruart on 27.05.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, DatabaseConfig.class})
@ActiveProfiles("hsqldb")
@Sql(scripts = "classpath:sql/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class WorkoutServiceImplTest {
    private static final int WORKOUT_CORRECT_ID = 3;
    private static final int WORKOUT_INCORRECT_ID = 77;
    private static final int USER_CORRECT_ID = 1;

    @Autowired
    private WorkoutService service;

    @Test
    public void testSave() throws Exception {
        Workout workout = service.save(getTestData(), USER_CORRECT_ID);
        Workout actualWorkout = service.get(workout.getId(), USER_CORRECT_ID);
        assertEquals(workout, actualWorkout);
        assertEquals(workout.getName(), actualWorkout.getName());
    }

    @Test
    public void testUpdateSuccess() throws Exception {
        Workout updatedWorkout = service.get(WORKOUT_CORRECT_ID, USER_CORRECT_ID);
        updatedWorkout.setName("TTriceps");                       //Change name
        service.update(updatedWorkout, USER_CORRECT_ID);          //update workout entity
        Workout actualWorkout = service.get(updatedWorkout.getId(), USER_CORRECT_ID);
        assertEquals(updatedWorkout, actualWorkout);
        assertEquals(updatedWorkout.getName(), actualWorkout.getName());
    }

    @Test(expected = InvalidParameterException.class)
    public void testUpdateFailure() throws Exception {
        Workout save = getTestData();
        save.setId(WORKOUT_INCORRECT_ID);
        service.update(save, USER_CORRECT_ID);      //try to update workout entity with nonexistent ID
    }

    @Test
    public void testDeleteSuccess() throws Exception {
        assertEquals("Size of list: 2", 2, service.getAll(USER_CORRECT_ID).size());
        service.delete(WORKOUT_CORRECT_ID, USER_CORRECT_ID);
        assertEquals("Size of list after delete: 1", 1, service.getAll(USER_CORRECT_ID).size());
    }

    @Test(expected = InvalidParameterException.class)
    public void testDeleteFailure() throws Exception {
        service.delete(WORKOUT_INCORRECT_ID, USER_CORRECT_ID); //non-existing ID
    }

    @Test
    public void testGetSuccess() throws Exception {
        Workout workout = service.get(WORKOUT_CORRECT_ID, USER_CORRECT_ID);
        assertEquals((Integer) WORKOUT_CORRECT_ID, workout.getId());
        assertEquals("chest", workout.getName());
    }

    @Test(expected = InvalidParameterException.class)
    public void testGetFailure() throws Exception {
        Workout workout = service.get(WORKOUT_INCORRECT_ID, USER_CORRECT_ID);
    }

    @Test
    public void testGetAll() throws Exception {
        assertEquals(2, service.getAll(USER_CORRECT_ID).size());
    }


    public Workout getTestData() {
        return new Workout(null, "ChestHard", null, LocalDateTime.now(), LocalDateTime.of(2017, 7, 29, 17, 22, 53));
    }

}