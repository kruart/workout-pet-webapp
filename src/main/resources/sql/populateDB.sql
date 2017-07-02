DELETE FROM tbl_approach;
DELETE FROM tbl_muscles;
DELETE FROM tbl_exercise;
DELETE FROM tbl_workout;
ALTER SEQUENCE entity_id_seq RESTART WITH 1;

INSERT INTO public.tbl_workout(name, startworkout, endworkout) VALUES ('chest', '2017-05-19 12:09:00', '2017-05-19 13:17:00');
INSERT INTO public.tbl_workout(name, startworkout, endworkout) VALUES ('biceps', '2017-05-19 19:35:00', '2017-05-19 20:22:00');

INSERT INTO public.tbl_exercise(comment, distancemeasure, repeatmeasure, timemeasure, weightmeasure, complexity, description, exercise_name, type, workout_id)
VALUES ('Важка тренька', 'false', 'true', 'false', 'false', 'simple', 'Do it right!', 'Підтягування', 'Basic', 1);
INSERT INTO public.tbl_exercise(comment, distancemeasure, repeatmeasure, timemeasure, weightmeasure, complexity, description, exercise_name, type, workout_id)
VALUES ('Не вистачело сил', 'false', 'true', 'false', 'true', 'middle', 'Do it right!', 'Жим  лежачи', 'Basic', 1);
INSERT INTO public.tbl_exercise(comment, distancemeasure, repeatmeasure, timemeasure, weightmeasure, complexity, description, exercise_name, type, workout_id)
VALUES ('Неймовірний пампінг м''язів', 'false', 'true', 'false', 'true', 'middle', 'Do it right!', 'Розводка гантель', 'Basic', 1);
INSERT INTO public.tbl_exercise(comment, distancemeasure, repeatmeasure, timemeasure, weightmeasure, complexity, description, exercise_name, type, workout_id)
VALUES ('Вижигає біцепс', 'false', 'true', 'false', 'true', 'simple', 'Do it right!', 'Молот на біцепс', 'Basic', 2);
INSERT INTO public.tbl_exercise(comment, distancemeasure, repeatmeasure, timemeasure, weightmeasure, complexity, description, exercise_name, type, workout_id)
VALUES ('зробити відкат, наст. разу', 'false', 'true', 'false', 'true', 'middle', 'Do it right!', 'Штанга на біцепс', 'Basic', 2);

INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (3, 'main', 'CHEST');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (3, 'optional', 'SHOULDERS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (3, 'optional', 'TRAPEZIUS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (4, 'main', 'CHEST');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (4, 'optional', 'SHOULDERS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (4, 'optional', 'TRICEPS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (5, 'main', 'CHEST');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (5, 'optional', 'SHOULDERS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (6, 'main', 'BICEPS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (6, 'optional', 'FOREARMS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (7, 'main', 'BICEPS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (7, 'optional', 'FOREARMS');

INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 15, 0, 0, 3);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 9, 0, 0, 3);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 8, 0, 0, 3);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 4, 0, 0, 3);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 11, 0, 89.7, 4);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 8, 0, 89.7, 4);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 8, 0, 89.7, 4);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 5, 0, 89.7, 4);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 8, 0, 15.2, 5);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 5, 0, 15.2, 5);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 3, 0, 15.2, 5);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 10, 0, 12.7, 5);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 9, 0, 24.0, 6);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 9, 0, 24.0, 6);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 7, 0, 24.0, 6);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 5, 0, 24.0, 6);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 7, 0, 45.7, 7);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 8, 0, 45.7, 7);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 5, 0, 45.7, 7);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 5, 0, 45.7, 7);





