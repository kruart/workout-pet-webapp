alter table tbl_approach drop constraint FK1n9c2cy2r39rn0wh5ro3rl820;
alter table tbl_exercise drop constraint FKgh5wmo31kl2famkl6iohd7a14;
alter table tbl_muscles drop constraint FKfv725yd3ymnhmujh42ujj5aqi;
drop table if exists tbl_approach cascade;
drop table if exists tbl_exercise cascade;
drop table if exists tbl_muscles cascade;
drop table if exists tbl_workout cascade;
drop sequence if exists entity_id_seq;

create sequence entity_id_seq start 1 increment 1;

create table tbl_approach (
  id INTEGER DEFAULT nextval('entity_id_seq'),
  distance float4, repeats int4,
  time time,
  weight float4,
  exercise_id int4 not null,
  primary key (id)
);


create table tbl_exercise (
  id INTEGER DEFAULT nextval('entity_id_seq'),
  comment varchar(255),
  distanceMeasure boolean,
  repeatMeasure boolean,
  timeMeasure boolean,
  weightMeasure boolean,
  complexity varchar(255) not null,
  description varchar(255),
  exercise_name varchar(255) not null,
  type varchar(255) not null,
  workout_id int4 not null, primary key (id)
);

create table tbl_muscles (
  exercise_id INTEGER,
  goal_of_working_muscle varchar(255),
  muscle_name varchar(50) not null
);

create table tbl_workout (
  id INTEGER DEFAULT nextval('entity_id_seq'),
  name varchar(255) not null,
  startWorkout timestamp DEFAULT now(),
  endWorkout timestamp,
  primary key (id)
);

alter table tbl_muscles add constraint working_muscle_unique_exercise_id_idx unique (exercise_id, muscle_name);
alter table tbl_approach add constraint FK1n9c2cy2r39rn0wh5ro3rl820 foreign key (exercise_id) references tbl_exercise;
alter table tbl_exercise add constraint FKgh5wmo31kl2famkl6iohd7a14 foreign key (workout_id) references tbl_workout;
alter table tbl_muscles add constraint FKfv725yd3ymnhmujh42ujj5aqi foreign key (exercise_id) references tbl_exercise;
