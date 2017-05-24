drop table if exists tbl_muscles cascade;
drop table if exists tbl_approach cascade;
drop table if exists tbl_exercise cascade;
drop table if exists tbl_workout cascade;
drop sequence if exists entity_id_seq;

create sequence entity_id_seq start 1 increment 1;

create table tbl_workout (
  id INTEGER PRIMARY KEY DEFAULT nextval('entity_id_seq'),
  name varchar(255) not null,
  startWorkout timestamp DEFAULT now(),
  endWorkout timestamp
);

create table tbl_exercise (
  id INTEGER PRIMARY KEY DEFAULT nextval('entity_id_seq'),
  comment varchar(255),
  distanceMeasure boolean,
  repeatMeasure boolean,
  timeMeasure boolean,
  weightMeasure boolean,
  complexity varchar(255) not null,
  description varchar(255),
  exercise_name varchar(255) not null,
  type varchar(255) not null,
  workout_id int4 not null,
  FOREIGN KEY (workout_id) REFERENCES tbl_workout (id) ON DELETE CASCADE
);

create table tbl_approach (
  id INTEGER PRIMARY KEY DEFAULT nextval('entity_id_seq'),
  distance float4, repeats int4,
  time time,
  weight float4,
  exercise_id int4 not null,
  FOREIGN KEY (exercise_id) REFERENCES tbl_exercise (id) ON DELETE CASCADE
);

create table tbl_muscles (
  exercise_id INTEGER,
  goal_of_working_muscle varchar(255),
  muscle_name varchar(50) not null,
  CONSTRAINT working_muscle_unique_exercise_id_idx UNIQUE (exercise_id, muscle_name),
  FOREIGN KEY (exercise_id) REFERENCES tbl_exercise (id) ON DELETE CASCADE
);
