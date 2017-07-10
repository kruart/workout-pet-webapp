DELETE FROM tbl_approach;
DELETE FROM tbl_muscles;
DELETE FROM tbl_exercise;
DELETE FROM tbl_workout;
DELETE FROM tbl_user_roles;
DELETE FROM tbl_users;
ALTER SEQUENCE entity_id_seq RESTART WITH 1;

INSERT INTO tbl_users (name, email, password)
VALUES ('user', 'user@ukr.net', 'password');

INSERT INTO tbl_users (name, email, password)
VALUES ('admin', 'admin@gmail.com', 'password');

INSERT INTO tbl_user_roles (role, user_id) VALUES
  ('ROLE_USER', 1),
  ('ROLE_ADMIN', 2),
  ('ROLE_USER', 2);

INSERT INTO public.tbl_workout(name, startworkout, endworkout, user_id) VALUES ('chest', '2017-05-19 12:09:00', '2017-05-19 13:17:00', 1);
INSERT INTO public.tbl_workout(name, startworkout, endworkout, user_id) VALUES ('biceps', '2017-05-19 19:35:00', '2017-05-19 20:22:00', 1);
INSERT INTO public.tbl_workout(name, startworkout, endworkout, user_id) VALUES ('triceps', '2017-05-19 12:09:00', '2017-05-19 13:17:00', 2);
INSERT INTO public.tbl_workout(name, startworkout, endworkout, user_id) VALUES ('middle_back', '2017-05-01 11:00:00', '2017-07-19 20:22:00', 2);

INSERT INTO public.tbl_exercise(comment, distancemeasure, repeatmeasure, timemeasure, weightmeasure, complexity, description, exercise_name, type, workout_id)
VALUES ('Важка тренька', 'false', 'true', 'false', 'false', 'simple', 'Do it right!', 'Підтягування', 'Basic', 3);
INSERT INTO public.tbl_exercise(comment, distancemeasure, repeatmeasure, timemeasure, weightmeasure, complexity, description, exercise_name, type, workout_id)
VALUES ('Не вистачело сил', 'false', 'true', 'false', 'true', 'middle', 'Do it right!', 'Жим  лежачи', 'Basic', 3);
INSERT INTO public.tbl_exercise(comment, distancemeasure, repeatmeasure, timemeasure, weightmeasure, complexity, description, exercise_name, type, workout_id)
VALUES ('Неймовірний пампінг м''язів', 'false', 'true', 'false', 'true', 'middle', 'Do it right!', 'Розводка гантель', 'Basic', 3);
INSERT INTO public.tbl_exercise(comment, distancemeasure, repeatmeasure, timemeasure, weightmeasure, complexity, description, exercise_name, type, workout_id)
VALUES ('Вижигає біцепс', 'false', 'true', 'false', 'true', 'simple', 'Do it right!', 'Молот на біцепс', 'Basic', 4);
INSERT INTO public.tbl_exercise(comment, distancemeasure, repeatmeasure, timemeasure, weightmeasure, complexity, description, exercise_name, type, workout_id)
VALUES ('зробити відкат, наст. разу', 'false', 'true', 'false', 'true', 'middle', 'Do it right!', 'Штанга на біцепс', 'Basic', 4);
INSERT INTO public.tbl_exercise(comment, distancemeasure, repeatmeasure, timemeasure, weightmeasure, complexity, description, exercise_name, type, workout_id)
VALUES ('зробити відкат, наст. разу', 'false', 'true', 'false', 'true', 'middle', 'Do it right!', 'Штанга на біцепс', 'Basic', 5);
INSERT INTO public.tbl_exercise(comment, distancemeasure, repeatmeasure, timemeasure, weightmeasure, complexity, description, exercise_name, type, workout_id)
VALUES ('Не вистачело сил', 'false', 'true', 'false', 'true', 'middle', 'Do it right!', 'Жим  лежачи', 'Basic', 5);
INSERT INTO public.tbl_exercise(comment, distancemeasure, repeatmeasure, timemeasure, weightmeasure, complexity, description, exercise_name, type, workout_id)
VALUES ('зробити відкат, наст. разу', 'false', 'true', 'false', 'true', 'middle', 'Do it right!', 'Штанга на біцепс', 'Basic', 6);
INSERT INTO public.tbl_exercise(comment, distancemeasure, repeatmeasure, timemeasure, weightmeasure, complexity, description, exercise_name, type, workout_id)
VALUES ('Важка тренька', 'false', 'true', 'false', 'false', 'simple', 'Do it right!', 'Підтягування', 'Basic', 6);
INSERT INTO public.tbl_exercise(comment, distancemeasure, repeatmeasure, timemeasure, weightmeasure, complexity, description, exercise_name, type, workout_id)
VALUES ('Не вистачело сил', 'false', 'true', 'false', 'true', 'middle', 'Do it right!', 'Жим  лежачи', 'Basic', 6);

INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (7, 'main', 'CHEST');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (7, 'optional', 'SHOULDERS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (7, 'optional', 'TRAPEZIUS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (8, 'main', 'CHEST');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (8, 'optional', 'SHOULDERS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (8, 'optional', 'TRICEPS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (9, 'main', 'CHEST');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (9, 'optional', 'SHOULDERS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (10, 'main', 'BICEPS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (10, 'optional', 'FOREARMS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (11, 'main', 'BICEPS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (11, 'optional', 'FOREARMS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (12, 'main', 'CHEST');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (12, 'optional', 'SHOULDERS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (12, 'optional', 'TRAPEZIUS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (13, 'main', 'CHEST');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (13, 'optional', 'SHOULDERS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (13, 'optional', 'TRAPEZIUS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (14, 'main', 'CHEST');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (14, 'optional', 'SHOULDERS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (15, 'main', 'CHEST');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (15, 'optional', 'SHOULDERS');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (16, 'main', 'CHEST');
INSERT INTO public.tbl_muscles(exercise_id, goal_of_working_muscle, muscle_name) VALUES (16, 'optional', 'SHOULDERS');

INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 15, 0, 0, 7);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 9, 0, 0, 7);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 8, 0, 0, 7);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 4, 0, 0, 7);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 11, 0, 89.7, 8);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 8, 0, 89.7, 8);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 8, 0, 89.7, 8);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 5, 0, 89.7, 8);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 8, 0, 15.2, 9);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 5, 0, 15.2, 9);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 3, 0, 15.2, 9);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 10, 0, 12.7, 9);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 9, 0, 24.0, 10);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 9, 0, 24.0, 10);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 7, 0, 24.0, 10);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 5, 0, 24.0, 10);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 7, 0, 45.7, 11);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 8, 0, 45.7, 11);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 5, 0, 45.7, 11);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 5, 0, 45.7, 11);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 7, 0, 45.7, 12);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 8, 0, 45.7, 12);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 5, 0, 45.7, 12);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 5, 0, 45.7, 12);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 8, 0, 15.2, 13);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 5, 0, 15.2, 13);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 3, 0, 15.2, 13);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 10, 0, 12.7, 13);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 8, 0, 15.2, 14);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 5, 0, 15.2, 14);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 3, 0, 15.2, 14);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 10, 0, 12.7, 14);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 5, 0, 15.2, 15);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 3, 0, 15.2, 15);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 10, 0, 12.7, 15);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 5, 0, 15.2, 16);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 3, 0, 15.2, 16);
INSERT INTO public.tbl_approach(distance, repeats, time, weight, exercise_id) VALUES (0, 10, 0, 12.7, 16);





