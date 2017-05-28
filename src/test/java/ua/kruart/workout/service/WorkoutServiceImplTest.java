package ua.kruart.workout.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
@ContextConfiguration(locations = {"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@ActiveProfiles("hsqldb")
@Sql(scripts = "classpath:sql/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class WorkoutServiceImplTest {

    @Autowired
    private WorkoutService service;

    @Test
    public void testSave() throws Exception {
        Workout workout = service.save(getTestData());
        assertEquals(workout, service.get(workout.getId()));
        assertEquals(workout.getName(), service.get(workout.getId()).getName());
    }

    @Test
    public void testUpdateSuccess() throws Exception {
        Workout save = service.save(getTestData());    //save new workout entity
        assertEquals(save.getName(), service.get(save.getId()).getName());
        save.setName("Triceps");                       //Change name
        service.update(save);                           //update workout entity
        assertEquals(save, service.get(save.getId()));
        assertEquals(save.getName(), service.get(save.getId()).getName());
    }

    @Test(expected = InvalidParameterException.class)
    public void testUpdateFailure() throws Exception {
        Workout save = getTestData();
        save.setId(77);
        service.update(save);      //try to update workout entity with nonexistent ID
    }

    @Test
    public void testDeleteSuccess() throws Exception {
        assertEquals("Size of list: 2", 2, service.getAll().size());
        service.delete(1);
        assertEquals("Size of list after delete: 1", 1, service.getAll().size());
    }

    @Test(expected = InvalidParameterException.class)
    public void testDeleteFailure() throws Exception {
        service.delete(77); //non-existing ID
    }

    @Test
    public void testGetSuccess() throws Exception {
        Workout workout = service.get(1);
        assertEquals(1, workout.getId());
        assertEquals("chest", workout.getName());
    }

    @Test(expected = InvalidParameterException.class)
    public void testGetFailure() throws Exception {
        Workout workout = service.get(77);
    }

    @Test
    public void testGetAll() throws Exception {
        assertEquals(2, service.getAll().size());
    }


    public Workout getTestData() {
        return new Workout(null, "ChestHard", null, LocalDateTime.now(), LocalDateTime.of(2017, 07, 29, 17, 22, 53));
    }

}