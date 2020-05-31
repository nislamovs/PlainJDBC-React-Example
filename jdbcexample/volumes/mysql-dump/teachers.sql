CREATE DATABASE IF NOT EXISTS school ;
use school ;

create table teachers (
	id INT,
	firstname VARCHAR(50),
	lastname VARCHAR(50),
	email VARCHAR(50),
	birthdate DATE,
	class_id INT,
	subject_id INT,
	is_head VARCHAR(50)
);
insert into teachers (id, firstname, lastname, email, birthdate, class_id, subject_id, is_head) values (1, 'Marshall', 'Dalmon', 'mdalmon0@arizona.edu', STR_TO_DATE('09/24/1982', '%m/%d/%Y'), 1, 1, true);
insert into teachers (id, firstname, lastname, email, birthdate, class_id, subject_id, is_head) values (2, 'Malvina', 'Woodworth', 'mwoodworth1@jalbum.net', STR_TO_DATE('05/03/1970', '%m/%d/%Y'), 2, 2, true);
insert into teachers (id, firstname, lastname, email, birthdate, class_id, subject_id, is_head) values (3, 'Ludovika', 'Dagon', 'ldagon2@cdbaby.com', STR_TO_DATE('02/25/1963', '%m/%d/%Y'), 3, 3, true);
insert into teachers (id, firstname, lastname, email, birthdate, class_id, subject_id, is_head) values (4, 'Jameson', 'Ludlom', 'jludlom3@rambler.ru', STR_TO_DATE('02/14/1979', '%m/%d/%Y'), 4, 4, true);
insert into teachers (id, firstname, lastname, email, birthdate, class_id, subject_id, is_head) values (5, 'Melonie', 'Doveston', 'mdoveston4@youku.com', STR_TO_DATE('03/22/1971', '%m/%d/%Y'), 5, 5, true);
insert into teachers (id, firstname, lastname, email, birthdate, class_id, subject_id, is_head) values (6, 'Arin', 'Marcinkowski', 'amarcinkowski5@elpais.com', STR_TO_DATE('08/12/1970', '%m/%d/%Y'), null, 6, false);
insert into teachers (id, firstname, lastname, email, birthdate, class_id, subject_id, is_head) values (7, 'Lise', 'Prevost', 'lprevost6@craigslist.org', STR_TO_DATE('07/15/1982', '%m/%d/%Y'), null, 7, false);
insert into teachers (id, firstname, lastname, email, birthdate, class_id, subject_id, is_head) values (8, 'Carine', 'Willimott', 'cwillimott7@addthis.com', STR_TO_DATE('04/04/1962', '%m/%d/%Y'), null, 8, false);
insert into teachers (id, firstname, lastname, email, birthdate, class_id, subject_id, is_head) values (9, 'Thorn', 'Joscelin', 'tjoscelin8@hibu.com', STR_TO_DATE('08/17/1964', '%m/%d/%Y'), null, 9, false);
insert into teachers (id, firstname, lastname, email, birthdate, class_id, subject_id, is_head) values (10, 'Kinsley', 'Bullingham', 'kbullingham9@boston.com', STR_TO_DATE('07/21/1973', '%m/%d/%Y'), null, 10, false);
insert into teachers (id, firstname, lastname, email, birthdate, class_id, subject_id, is_head) values (11, 'Torry', 'Croke', 'tcrokea@networksolutions.com', STR_TO_DATE('08/31/1981', '%m/%d/%Y'), null, 11, false);
insert into teachers (id, firstname, lastname, email, birthdate, class_id, subject_id, is_head) values (12, 'Adrian', 'Dobrowolski', 'adobrowolskib@sina.com.cn', STR_TO_DATE('08/19/1972', '%m/%d/%Y'), null, 12, false);
