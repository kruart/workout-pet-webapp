DROP TABLE tbl_muscles IF EXISTS;
DROP TABLE tbl_approach IF EXISTS;
DROP TABLE tbl_exercise IF EXISTS;
DROP TABLE tbl_workout IF EXISTS;
DROP SEQUENCE entity_id_seq IF EXISTS;

create sequence entity_id_seq start with 1 increment by 1;

create table tbl_workout (
  id INTEGER GENERATED BY DEFAULT AS SEQUENCE entity_id_seq PRIMARY KEY,
  name varchar(255) not null,
  startWorkout timestamp DEFAULT now(),
  endWorkout timestamp
);

create table tbl_exercise (
  id INTEGER GENERATED BY DEFAULT AS SEQUENCE entity_id_seq PRIMARY KEY,
  comment varchar(255),
  distanceMeasure boolean,
  repeatMeasure boolean,
  timeMeasure boolean,
  weightMeasure boolean,
  complexity varchar(255) not null,
  description varchar(255),
  exercise_name varchar(255) not null,
  type varchar(255) not null,
  workout_id integer not null,
  FOREIGN KEY (workout_id) REFERENCES tbl_workout (id) ON DELETE CASCADE
);

create table tbl_approach (
id INTEGER GENERATED BY DEFAULT AS SEQUENCE entity_id_seq PRIMARY KEY,
distance float,
repeats integer,
time integer,
weight float,
exercise_id integer not null,
FOREIGN KEY (exercise_id) REFERENCES tbl_exercise (id) ON DELETE CASCADE
);

create table tbl_muscles (
exercise_id INTEGER NOT NULL,
goal_of_working_muscle varchar(255),
muscle_name varchar(50) not null,
FOREIGN KEY (exercise_id) REFERENCES tbl_exercise (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX working_muscle_unique_exercise_id_idx ON tbl_muscles(exercise_id, muscle_name);
