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

t.firstname AS class_head_teacher_firstname,
t.lastname AS class_head_teacher_lastname,
t.email AS class_head_teacher_email,

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
WHERE s.name = 'Physics'                                        -- < ---
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
HAVING class_name = '5A'                                -- < ---
ORDER BY average_mark DESC


------------------------------------------------------

-- Get parents without kids list

SELECT
    par.id,
    par.firstname AS Parent_firstname,
    par.lastname AS Parent_lastname,
    par.familyid,

    IFNULL(pup.firstname, 'No') AS Pupil_firstname,
    IFNULL(pup.lastname, 'children')  AS Pupil_lastname,
    IFNULL(pup.birthdate, 'no') AS Pupil_birthdate,
    IFNULL((CASE
                WHEN pup.gender = 'FEMALE' THEN 'Girl'
                WHEN pup.gender = 'MALE' THEN 'Boy'
            END), 'problem') AS Pupil_gender
FROM parents par
LEFT JOIN pupils pup ON pup.familyId = par.familyId
ORDER BY par.id DESC

------------------------------------------------------

-- Get pupils list with class name and class head name and status [passes, fails]


SET @@sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));

SELECT
m.pupil_id,
CONCAT(p.firstname, ' ', p.lastname) as pupil_name,
p.email AS pupil_email,

c.name AS class_name,

CONCAT(t.firstname, ' ', t.lastname) as class_head_name,
t.email AS teacher_email,

ROUND(AVG(m.value), 2) AS average_mark,

CASE
    WHEN AVG(m.value) >= 4 THEN 'PASSING'
    WHEN AVG(m.value) < 4 THEN 'FAILING'
END AS Status

FROM marks m
INNER JOIN pupils p ON m.pupil_id = p.id
INNER JOIN classes c ON p.class_id = c.id
INNER JOIN teachers t ON c.class_head_id = t.id
GROUP BY m.pupil_id
ORDER BY average_mark DESC


------------------------------------------------------

-- Get pupils list with marks per subject (min, max, avg, count, passes or not, class name and class head)

SET @@sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));

SELECT
   (@cnt := @cnt + 1) AS Id,
   CONCAT(p.firstname, ' ', p.lastname) AS Pupil,
   p.email AS Pupil_email,

   c.name AS Class,

   s.name AS Subject,
   MIN(m.value) AS Mark_MIN,
   MAX(m.value) AS Mark_MAX,
   ROUND(AVG(m.value), 2) AS Mark_AVG,
   COUNT(*) AS Total_marks,

   CONCAT(t.firstname, ' ', t.lastname) AS Teacher,
   t.email AS Teacher_email,

   IF(AVG(m.value) >= 4, 'Passed', 'Failed') AS Status

FROM pupils p
CROSS JOIN (SELECT @cnt := 0) AS dummy
INNER JOIN classes c ON p.class_id = c.id
INNER JOIN marks m ON m.pupil_id = p.id
INNER JOIN subjects s ON m.subject_id = s.id
INNER JOIN teachers t ON s.teacher_id = t.id
-- WHERE p.email = 'wmacknight2n@jugem.jp'
GROUP BY Subject, Pupil
ORDER BY Id