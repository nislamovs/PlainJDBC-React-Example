-- Get all marks for the pupils of definite class

select
 c.name as class_name,

 p.id,
 p.firstname as pupil_firstname,
 p.lastname as pupil_lastname,
 p.email as pupil_email,
 p.birthdate as pupil_birthdate,

 s.name as subject_name,
 m.value as mark_value,
 m.date as mark_date,

 t.firstname as class_head_firstname,
 t.lastname as class_head_lastname,
 t.email as class_head_email

from pupils p
inner join classes c on p.class_id = c.id
inner join marks m on p.id = m.pupil_id
inner join teachers t on p.class_head_id = t.id
inner join subjects s on m.subject_id = s.id
where c.name = '5A'                                                 -- <----
order by p.id asc

------------------------------------------------------

-- Get average marks for pupils by pupil id - all school

SET @@sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));

select
m.pupil_id,
p.firstname,
p.lastname,
p.email,
ROUND(avg(m.value), 2) as average_mark
from marks m
inner join pupils p on m.pupil_id = p.id
where m.pupil_id >= 80 and m.pupil_id <= 85                     -- <----
group by m.pupil_id


------------------------------------------------------

-- Get average marks for pupils - all school, TOP 5

SET @@sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));

SELECT
m.pupil_id,
p.firstname AS pupil_firstname,
p.lastname AS pupil_lastname,
p.email AS pupil_email,

c.name AS class_name,

t.firstname AS teacher_firstname,
t.lastname AS teacher_lastname,
t.email AS teacher_email,

ROUND(AVG(m.value), 2) AS average_mark
FROM marks m
INNER JOIN pupils p ON m.pupil_id = p.id
INNER JOIN classes c ON p.class_id = c.id
INNER JOIN teachers t ON c.class_head_id = t.id
GROUP BY m.pupil_id
ORDER BY average_mark DESC
LIMIT 5


------------------------------------------------------

-- Get average marks for pupils, whose avg mark above 7 - all school

SET @@sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));

SELECT
m.pupil_id,
p.firstname AS pupil_firstname,
p.lastname AS pupil_lastname,
p.email AS pupil_email,

c.name AS class_name,

t.firstname AS teacher_firstname,
t.lastname AS teacher_lastname,
t.email AS teacher_email,

ROUND(AVG(m.value), 2) AS average_mark
FROM marks m
INNER JOIN pupils p ON m.pupil_id = p.id
INNER JOIN classes c ON p.class_id = c.id
INNER JOIN teachers t ON c.class_head_id = t.id
GROUP BY m.pupil_id
HAVING average_mark >= 7
ORDER BY average_mark DESC


------------------------------------------------------

-- Get average marks for pupils per subject - all school, TOP 5

SET @@sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));

SELECT
m.pupil_id,

s.name AS subject,

p.firstname AS pupil_firstname,
p.lastname AS pupil_lastname,
p.email AS pupil_email,

c.name AS class_name,

t.firstname AS teacher_firstname,
t.lastname AS teacher_lastname,
t.email AS teacher_email,

ROUND(AVG(m.value), 2) AS average_mark
FROM marks m
INNER JOIN pupils p ON m.pupil_id = p.id
INNER JOIN classes c ON p.class_id = c.id
INNER JOIN teachers t ON c.class_head_id = t.id
INNER JOIN subjects s ON m.subject_id = s.id
WHERE s.name = 'Physics'
GROUP BY m.pupil_id
ORDER BY average_mark DESC
LIMIT 5

------------------------------------------------------

-- Get average marks for pupils - by class

SET @@sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));

SELECT
m.pupil_id,
p.firstname AS pupil_firstname,
p.lastname AS pupil_lastname,
p.email AS pupil_email,

c.name AS class_name,

t.firstname AS teacher_firstname,
t.lastname AS teacher_lastname,
t.email AS teacher_email,

ROUND(AVG(m.value), 2) AS average_mark
FROM marks m
INNER JOIN pupils p ON m.pupil_id = p.id
INNER JOIN classes c ON p.class_id = c.id
INNER JOIN teachers t ON c.class_head_id = t.id
GROUP BY m.pupil_id
HAVING class_name = '5A'
ORDER BY average_mark DESC



