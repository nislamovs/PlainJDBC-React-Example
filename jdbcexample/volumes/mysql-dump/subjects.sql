CREATE DATABASE IF NOT EXISTS school ;
use school ;

create table subjects (
	id INT,
	teacher_id INT,
	name VARCHAR(10)
);
insert into subjects (id, teacher_id, name) values (1, 1, 'Math');
insert into subjects (id, teacher_id, name) values (2, 2, 'Literature');
insert into subjects (id, teacher_id, name) values (3, 3, 'Sport');
insert into subjects (id, teacher_id, name) values (4, 4, 'English');
insert into subjects (id, teacher_id, name) values (5, 5, 'Latvian');
insert into subjects (id, teacher_id, name) values (6, 6, 'Russian');
insert into subjects (id, teacher_id, name) values (7, 7, 'Biology');
insert into subjects (id, teacher_id, name) values (8, 8, 'Physics');
insert into subjects (id, teacher_id, name) values (9, 9, 'Chemistry');
insert into subjects (id, teacher_id, name) values (10, 10, 'History');
insert into subjects (id, teacher_id, name) values (11, 11, 'Geography');
insert into subjects (id, teacher_id, name) values (12, 12, 'Economics');
