
-- ----------------------------------------------------------------------------------------------------------------------
-- ------------------------------------ Get all marks for the pupils of definite class [page]----------------------------
-- ----------------------------------------------------------------------------------------------------------------------

CREATE DATABASE IF NOT EXISTS school ;
use school ;

DELIMITER $$

DROP PROCEDURE IF EXISTS GET_PUPILS_MARKS_PER_CLASS_PAGE;
CREATE PROCEDURE GET_PUPILS_MARKS_PER_CLASS_PAGE(IN CLASS_NAME VARCHAR(30), IN PAGE_SIZE INTEGER, IN PAGE_NUM INTEGER)
NOT DETERMINISTIC
BEGIN
  START TRANSACTION;
    SET @OFFSET_VAL=(SELECT PAGE_SIZE * PAGE_NUM);
    SET @CLASSNAME=CLASS_NAME;
    SET @PAGESIZE=PAGE_SIZE;

    CREATE TEMPORARY TABLE TempTable
    (
       id INTEGER AUTO_INCREMENT NOT NULL,
       class_name VARCHAR(20),
       pupil_id INTEGER,
       pupil_firstname VARCHAR(30),
       pupil_lastname VARCHAR(30),
       pupil_email VARCHAR(50),
       pupil_birthdate DATE,
       subject_name VARCHAR(30),
       mark_value INTEGER,
       mark_date DATE,
       class_head_firstname VARCHAR(30),
       class_head_lastname VARCHAR(30),
       class_head_email VARCHAR(50),

       primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

    SET @stats_query = CONCAT(
    "INSERT INTO TempTable(class_name, pupil_id, pupil_firstname, pupil_lastname, pupil_email, pupil_birthdate,
      subject_name, mark_value, mark_date, class_head_firstname, class_head_lastname, class_head_email)
     SELECT
     c.name as class_name,

     p.id as pupil_id,
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
    WHERE c.name = '",

    @CLASSNAME,

    "' ORDER BY p.id ASC, s.name ASC
    LIMIT ",

    @PAGESIZE,

    " OFFSET ",

    @OFFSET_VAL,

    " ; ");

--  End of CONCAT

    PREPARE stmt FROM @stats_query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;

    SELECT * FROM TempTable;
    DROP TABLE TempTable;

  COMMIT;
END$$

-- use school;
-- call GET_PUPILS_MARKS_PER_CLASS_PAGE('5A', 5, 1);

-- ---------------------------------------------------------------------------------------------------------------------
-- ------------------------------------ Get all marks for the pupils of definite class----------------------------------
-- ---------------------------------------------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS GET_PUPILS_MARKS_PER_CLASS;
CREATE PROCEDURE GET_PUPILS_MARKS_PER_CLASS(IN CLASS_NAME VARCHAR(30))
NOT DETERMINISTIC
BEGIN
  START TRANSACTION;
    SELECT
     c.name as class_name,

     p.id as pupil_id,
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
    WHERE c.name = CLASS_NAME
    ORDER BY p.id ASC, s.name ASC ;

   COMMIT;

END$$

-- use school;
-- call GET_PUPILS_MARKS_PER_CLASS('5A');

-- ---------------------------------------------------------------------------------------------------------------------
-- -------------------------- Get average marks for pupils by pupil id - all school [page]------------------------------
-- ---------------------------------------------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS GET_AVG_PUPILS_MARKS_BY_PUPIL_ID_INTERVAL_ALL_SCHOOL_PAGE;
CREATE PROCEDURE GET_AVG_PUPILS_MARKS_BY_PUPIL_ID_INTERVAL_ALL_SCHOOL_PAGE(IN ID_START INTEGER, IN ID_END INTEGER,
                                                                           IN PAGE_SIZE INTEGER, IN PAGE_NUM INTEGER)
NOT DETERMINISTIC
BEGIN
  START TRANSACTION;
    SET @@sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));

    SET @OFFSET_VAL=(SELECT PAGE_SIZE * PAGE_NUM);
    SET @ID_START=ID_START;
    SET @ID_END=ID_END;
    SET @PAGESIZE=PAGE_SIZE;

    CREATE TEMPORARY TABLE TempTable
    (
       id INTEGER AUTO_INCREMENT NOT NULL,
       pupil_id INTEGER,
       pupil_firstname VARCHAR(30),
       pupil_lastname VARCHAR(30),
       pupil_email VARCHAR(50),
       average_mark DECIMAL,

       primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

    SET @stats_query = CONCAT(
    "INSERT INTO TempTable(pupil_id, pupil_firstname, pupil_lastname, pupil_email, average_mark)
     SELECT
     m.pupil_id as pupil_id,
     p.firstname as pupil_firstname,
     p.lastname as pupil_lastname,
     p.email as pupil_email,
     ROUND(avg(m.value), 2) as average_mark
     from marks m
     inner join pupils p on m.pupil_id = p.id
     where m.pupil_id >= ",
     @ID_START,
     " and m.pupil_id <= ",
     @ID_END,
     " group by m.pupil_id ",
     " LIMIT ",
     @PAGESIZE,
     " OFFSET ",
     @OFFSET_VAL,
     " ; ");

     --  End of CONCAT

    PREPARE stmt FROM @stats_query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;

    SELECT * FROM TempTable;
    DROP TABLE TempTable;

  COMMIT;

END$$

-- use school;
-- call GET_AVG_PUPILS_MARKS_BY_PUPIL_ID_INTERVAL_ALL_SCHOOL_PAGE(1, 90, 5, 5);

-- ---------------------------------------------------------------------------------------------------------------------
-- -------------------------- Get average marks for pupils by pupil id - all school ------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS GET_AVG_PUPILS_MARKS_BY_PUPIL_ID_INTERVAL_ALL_SCHOOL;
CREATE PROCEDURE GET_AVG_PUPILS_MARKS_BY_PUPIL_ID_INTERVAL_ALL_SCHOOL(IN ID_START INTEGER, IN ID_END INTEGER)
NOT DETERMINISTIC
BEGIN
  START TRANSACTION;
    SET @@sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));

    select
    m.pupil_id as pupil_id,
    p.firstname as pupil_firstname,
    p.lastname as pupil_lastname,
    p.email as pupil_email,
    ROUND(avg(m.value), 2) as average_mark
    from marks m
    inner join pupils p on m.pupil_id = p.id
    where m.pupil_id >= ID_START and m.pupil_id <= ID_END                     -- <----
    group by m.pupil_id ;

  COMMIT;

END$$

-- use school;
-- call GET_AVG_PUPILS_MARKS_BY_PUPIL_ID_INTERVAL_ALL_SCHOOL(81, 90);

-- ---------------------------------------------------------------------------------------------------------------------
-- -------------------------- Get average marks for pupils - all school - TOP 5-----------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS GET_AVG_PUPILS_MARKS_ALL_SCHOOL_TOP5;
CREATE PROCEDURE GET_AVG_PUPILS_MARKS_ALL_SCHOOL_TOP5()
NOT DETERMINISTIC
BEGIN
  START TRANSACTION;
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
    LIMIT 5 ;

  COMMIT;

END$$

-- use school;
-- call GET_AVG_PUPILS_MARKS_ALL_SCHOOL_TOP5();

-- ---------------------------------------------------------------------------------------------------------------------
-- -------------------- Get average marks for pupils, whose avg mark above 7 - all school [page]------------------------
-- ---------------------------------------------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS GET_AVG_PUPILS_MARKS_ALL_SCHOOL_ABOVE_7_PAGE;
CREATE PROCEDURE GET_AVG_PUPILS_MARKS_ALL_SCHOOL_ABOVE_7_PAGE(IN PAGE_SIZE INTEGER, IN PAGE_NUM INTEGER)
NOT DETERMINISTIC
BEGIN
  START TRANSACTION;

    SET @@sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
    SET @OFFSET_VAL=(SELECT PAGE_SIZE * PAGE_NUM);
    SET @PAGESIZE=PAGE_SIZE;

    CREATE TEMPORARY TABLE TempTable
    (
       id INTEGER AUTO_INCREMENT NOT NULL,
       pupil_id INTEGER,
       pupil_firstname VARCHAR(30),
       pupil_lastname VARCHAR(30),
       pupil_email VARCHAR(50),
       class_name VARCHAR(10),
       class_head_teacher_firstname VARCHAR(30),
       class_head_teacher_lastname VARCHAR(30),
       class_head_teacher_email VARCHAR(50),
       average_mark DECIMAL,

       primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

    SET @stats_query = CONCAT(
    "INSERT INTO TempTable(pupil_id, pupil_firstname, pupil_lastname, pupil_email, class_name, class_head_teacher_firstname, class_head_teacher_lastname, class_head_teacher_email, average_mark)
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
    ORDER BY average_mark DESC",
    " LIMIT ",
    @PAGESIZE,
    " OFFSET ",
    @OFFSET_VAL,
    " ; ");

    PREPARE stmt FROM @stats_query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;

    SELECT * FROM TempTable;
    DROP TABLE TempTable;

  COMMIT;

END$$

-- use school;
-- call GET_AVG_PUPILS_MARKS_ALL_SCHOOL_ABOVE_7_PAGE(5, 5);

-- ---------------------------------------------------------------------------------------------------------------------
-- -------------------- Get average marks for pupils, whose avg mark above 7 - all school ------------------------------
-- ---------------------------------------------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS GET_AVG_PUPILS_MARKS_ALL_SCHOOL_ABOVE_7;
CREATE PROCEDURE GET_AVG_PUPILS_MARKS_ALL_SCHOOL_ABOVE_7()
NOT DETERMINISTIC
BEGIN
  START TRANSACTION;

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
    ORDER BY average_mark DESC ;

  COMMIT;

END$$

-- use school;
-- call GET_AVG_PUPILS_MARKS_ALL_SCHOOL_ABOVE_7();

-- ---------------------------------------------------------------------------------------------------------------------
-- ------------------------- Get average marks for pupils per subject - all school, TOP 5-------------------------------
-- ---------------------------------------------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS GET_AVG_PUPILS_MARKS_ALL_SCHOOL_PER_SUBJECT_TOP5;
CREATE PROCEDURE GET_AVG_PUPILS_MARKS_ALL_SCHOOL_PER_SUBJECT_TOP5(IN SUBJ_NAME VARCHAR(20))
NOT DETERMINISTIC
BEGIN
  START TRANSACTION;

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
    WHERE s.name = SUBJ_NAME                                        -- < ---
    GROUP BY m.pupil_id
    ORDER BY average_mark DESC
    LIMIT 5 ;

  COMMIT;

END$$

-- use school;
-- call GET_AVG_PUPILS_MARKS_ALL_SCHOOL_PER_SUBJECT_TOP5('Physics');

-- ---------------------------------------------------------------------------------------------------------------------
-- --------------------------------- Get average marks for pupils - by class  ------------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS GET_AVG_PUPILS_MARKS_BY_CLASS;
CREATE PROCEDURE GET_AVG_PUPILS_MARKS_BY_CLASS(IN CLASS_NAME VARCHAR(10))
NOT DETERMINISTIC
BEGIN
  START TRANSACTION;

    SET @@sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
    SET @CLASSNAME=CLASS_NAME;

    CREATE TEMPORARY TABLE TempTable
    (
       id INTEGER AUTO_INCREMENT NOT NULL,
       pupil_id INTEGER,
       pupil_firstname VARCHAR(30),
       pupil_lastname VARCHAR(30),
       pupil_email VARCHAR(50),
       class_name VARCHAR(10),
       teacher_firstname VARCHAR(30),
       teacher_lastname VARCHAR(30),
       teacher_email VARCHAR(50),
       average_mark DECIMAL,

       primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

    SET @stats_query = CONCAT(
    "INSERT INTO TempTable(pupil_id, pupil_firstname, pupil_lastname, pupil_email, class_name, teacher_firstname, teacher_lastname, teacher_email, average_mark)
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
    HAVING c.name = '",
    @CLASSNAME,
    "' ORDER BY average_mark DESC ; ");

    PREPARE stmt FROM @stats_query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;

    SELECT * FROM TempTable;
    DROP TABLE TempTable;

  COMMIT;

END$$

-- use school;
-- call GET_AVG_PUPILS_MARKS_BY_CLASS('5A');

-- ---------------------------------------------------------------------------------------------------------------------
-- --------------------------------- Get average marks for pupils - by class [page] ------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS GET_AVG_PUPILS_MARKS_BY_CLASS_PAGE;
CREATE PROCEDURE GET_AVG_PUPILS_MARKS_BY_CLASS_PAGE(IN CLASS_NAME VARCHAR(10), IN PAGE_SIZE INTEGER, IN PAGE_NUM INTEGER)
NOT DETERMINISTIC
BEGIN
  START TRANSACTION;

    SET @@sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
    SET @CLASSNAME=CLASS_NAME;
    SET @OFFSET_VAL=(SELECT PAGE_SIZE * PAGE_NUM);
    SET @PAGESIZE=PAGE_SIZE;

    CREATE TEMPORARY TABLE TempTable
    (
       id INTEGER AUTO_INCREMENT NOT NULL,
       pupil_id INTEGER,
       pupil_firstname VARCHAR(30),
       pupil_lastname VARCHAR(30),
       pupil_email VARCHAR(50),
       class_name VARCHAR(10),
       teacher_firstname VARCHAR(30),
       teacher_lastname VARCHAR(30),
       teacher_email VARCHAR(50),
       average_mark DECIMAL,

       primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

    SET @stats_query = CONCAT(
    "INSERT INTO TempTable(pupil_id, pupil_firstname, pupil_lastname, pupil_email, class_name, teacher_firstname, teacher_lastname, teacher_email, average_mark)
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
    HAVING c.name = '",
    @CLASSNAME,
    "' ORDER BY average_mark DESC",
    " LIMIT ",
    @PAGESIZE,
    " OFFSET ",
    @OFFSET_VAL,
    " ; ");

    PREPARE stmt FROM @stats_query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;

    SELECT * FROM TempTable;
    DROP TABLE TempTable;

  COMMIT;

END$$

-- use school;
-- call GET_AVG_PUPILS_MARKS_BY_CLASS_PAGE('5A', 5, 2);

-- ---------------------------------------------------------------------------------------------------------------------
-- ---------------------------------Get parents and kids list [page] -----------------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS GET_PARENTS_AND_KIDS_LIST_PAGE;
CREATE PROCEDURE GET_PARENTS_AND_KIDS_LIST_PAGE(IN PAGE_SIZE INTEGER, IN PAGE_NUM INTEGER)
NOT DETERMINISTIC
BEGIN
  START TRANSACTION;

    SET @@sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
    SET @OFFSET_VAL=(SELECT PAGE_SIZE * PAGE_NUM);
    SET @PAGESIZE=PAGE_SIZE;

    CREATE TEMPORARY TABLE TempTable
    (
       id INTEGER AUTO_INCREMENT NOT NULL,
       parent_id INTEGER,
       parent_firstname VARCHAR(30),
       parent_lastname VARCHAR(30),
       familyId VARCHAR(150),

       pupil_firstname VARCHAR(30),
       pupil_lastname VARCHAR(30),
       pupil_birthdate VARCHAR(30),
       pupil_gender VARCHAR(30),

       primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

    SET @stats_query = CONCAT(
        "INSERT INTO TempTable(parent_id, parent_firstname, parent_lastname, familyId, pupil_firstname, pupil_lastname, pupil_birthdate, pupil_gender)
        SELECT
        par.id as parent_id,
        par.firstname AS parent_firstname,
        par.lastname AS parent_lastname,
        par.familyid as familyId,

        IFNULL(pup.firstname, 'No') AS pupil_firstname,
        IFNULL(pup.lastname, 'children')  AS pupil_lastname,
        IFNULL(pup.birthdate, 'no') AS pupil_birthdate,
        IFNULL((CASE
                    WHEN pup.gender = 'FEMALE' THEN 'Girl'
                    WHEN pup.gender = 'MALE' THEN 'Boy'
                END), 'problem') AS pupil_gender
        FROM parents par
        LEFT JOIN pupils pup ON pup.familyId = par.familyId
        ORDER BY par.id DESC",
        " LIMIT ",
        @PAGESIZE,
        " OFFSET ",
        @OFFSET_VAL,
        " ; "
    );

    PREPARE stmt FROM @stats_query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;

    SELECT * FROM TempTable;
    DROP TABLE TempTable;

  COMMIT;
END$$


-- use school;
-- call GET_PARENTS_AND_KIDS_LIST_PAGE(5, 2);

-- ---------------------------------------------------------------------------------------------------------------------
-- ---------------------------------Get parents and kids list ----------------------------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS GET_PARENTS_AND_KIDS_LIST;
CREATE PROCEDURE GET_PARENTS_AND_KIDS_LIST()
NOT DETERMINISTIC
BEGIN
  START TRANSACTION;

    SET @@sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));

    SELECT
    par.id as parent_id,
    par.firstname AS parent_firstname,
    par.lastname AS parent_lastname,
    par.familyid as familyId,

    IFNULL(pup.firstname, 'No') AS pupil_firstname,
    IFNULL(pup.lastname, 'children')  AS pupil_lastname,
    IFNULL(pup.birthdate, 'no') AS pupil_birthdate,
    IFNULL((CASE
                WHEN pup.gender = 'FEMALE' THEN 'Girl'
                WHEN pup.gender = 'MALE' THEN 'Boy'
            END), 'problem') AS pupil_gender
    FROM parents par
    LEFT JOIN pupils pup ON pup.familyId = par.familyId
    ORDER BY par.id DESC ;

  COMMIT;
END$$


-- use school;
-- call GET_PARENTS_AND_KIDS_LIST();

-- ---------------------------------------------------------------------------------------------------------------------
-- --------------- Get pupils list with class name and class head name and status [passes, fails]-[page]----------------
-- ---------------------------------------------------------------------------------------------------------------------


DROP PROCEDURE IF EXISTS GET_PUPILS_REVIEW_PAGE;
CREATE PROCEDURE GET_PUPILS_REVIEW_PAGE(IN PAGE_SIZE INTEGER, IN PAGE_NUM INTEGER)
NOT DETERMINISTIC
BEGIN
  START TRANSACTION;

    SET @@sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
    SET @OFFSET_VAL=(SELECT PAGE_SIZE * PAGE_NUM);
    SET @PAGESIZE=PAGE_SIZE;

    CREATE TEMPORARY TABLE TempTable
    (
       id INTEGER AUTO_INCREMENT NOT NULL,
       pupil_id INTEGER,
       pupil_name VARCHAR(60),
       pupil_email VARCHAR(50),
       class_name VARCHAR(10),

       class_head_name VARCHAR(60),
       teacher_email VARCHAR(50),
       average_mark DECIMAL,
       status VARCHAR(10),

       primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

    SET @stats_query = CONCAT(
        "INSERT INTO TempTable(pupil_id, pupil_name, pupil_email, class_name, class_head_name, teacher_email, average_mark, status)
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
        END AS status

        FROM marks m
        INNER JOIN pupils p ON m.pupil_id = p.id
        INNER JOIN classes c ON p.class_id = c.id
        INNER JOIN teachers t ON c.class_head_id = t.id
        GROUP BY m.pupil_id
        ORDER BY average_mark DESC ",
        " LIMIT ",
        @PAGESIZE,
        " OFFSET ",
        @OFFSET_VAL,
        " ; "
    );

    PREPARE stmt FROM @stats_query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;

    SELECT * FROM TempTable;
    DROP TABLE TempTable;

  COMMIT;

END$$

-- use school;
-- call GET_PUPILS_REVIEW_PAGE(5, 5);

-- ---------------------------------------------------------------------------------------------------------------------
-- --------------- Get pupils list with class name and class head name and status [passes, fails] ----------------------
-- ---------------------------------------------------------------------------------------------------------------------


DROP PROCEDURE IF EXISTS GET_PUPILS_REVIEW;
CREATE PROCEDURE GET_PUPILS_REVIEW()
NOT DETERMINISTIC
BEGIN
  START TRANSACTION;

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
    END AS status

    FROM marks m
    INNER JOIN pupils p ON m.pupil_id = p.id
    INNER JOIN classes c ON p.class_id = c.id
    INNER JOIN teachers t ON c.class_head_id = t.id
    GROUP BY m.pupil_id
    ORDER BY average_mark DESC ;

  COMMIT;

END$$

-- use school;
-- call GET_PUPILS_REVIEW();

-- ---------------------------------------------------------------------------------------------------------------------
-- ----Get pupils list with marks per subject (min, max, avg, count, passes or not, class name and class head)----------
-- ---------------------------------------------  [page]   -------------------------------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------


DROP PROCEDURE IF EXISTS GET_PUPILS_FULL_REVIEW_PAGE;
CREATE PROCEDURE GET_PUPILS_FULL_REVIEW_PAGE(IN PAGE_SIZE INTEGER, IN PAGE_NUM INTEGER)
NOT DETERMINISTIC
BEGIN
  START TRANSACTION;

    SET @@sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
    SET @OFFSET_VAL=(SELECT PAGE_SIZE * PAGE_NUM);
    SET @PAGESIZE=PAGE_SIZE;

    CREATE TEMPORARY TABLE TempTable
    (
       id INTEGER AUTO_INCREMENT NOT NULL,
       pupil_name VARCHAR(70),
       pupil_email VARCHAR(50),
       class_name VARCHAR(10),
       subject VARCHAR(30),

       Mark_MIN INTEGER,
       Mark_MAX INTEGER,
       Mark_AVG DECIMAL,
       Total_marks INTEGER,
       Marks VARCHAR(30),

       teacher_name VARCHAR(60),
       teacher_email VARCHAR(50),
       status VARCHAR(10),

       primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

    SET @stats_query = CONCAT(
        "INSERT INTO TempTable(pupil_name, pupil_email, class_name, subject, Mark_MIN, Mark_MAX, Mark_AVG, Total_marks, Marks, teacher_name, teacher_email, status)
        SELECT
           CONCAT(p.firstname, ' ', p.lastname) AS pupil_name,
           p.email AS pupil_email,
           c.name AS class_name,

           s.name AS subject,
           MIN(m.value) AS Mark_MIN,
           MAX(m.value) AS Mark_MAX,
           ROUND(AVG(m.value), 2) AS Mark_AVG,
           COUNT(*) AS Total_marks,
           GROUP_CONCAT(m.value) as Marks,
           CONCAT(t.firstname, ' ', t.lastname) AS teacher_name,
           t.email AS teacher_email,

           IF(AVG(m.value) >= 4, 'Passed', 'Failed') AS Status

        FROM pupils p
        CROSS JOIN (SELECT @cnt := 0) AS dummy
        INNER JOIN classes c ON p.class_id = c.id
        INNER JOIN marks m ON m.pupil_id = p.id
        INNER JOIN subjects s ON m.subject_id = s.id
        INNER JOIN teachers t ON s.teacher_id = t.id
        GROUP BY subject, pupil_name
        ORDER BY pupil_name",
        " LIMIT ",
        @PAGESIZE,
        " OFFSET ",
        @OFFSET_VAL,
        " ; "
    );

    PREPARE stmt FROM @stats_query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;

    SELECT * FROM TempTable;
    DROP TABLE TempTable;

  COMMIT;

END$$


-- use school;
-- call GET_PUPILS_FULL_REVIEW_PAGE(10, 5);

-- ---------------------------------------------------------------------------------------------------------------------
-- ----Get pupils list with marks per subject (min, max, avg, count, passes or not, class name and class head)----------
-- ---------------------------------------------------------------------------------------------------------------------


DROP PROCEDURE IF EXISTS GET_PUPILS_FULL_REVIEW;
CREATE PROCEDURE GET_PUPILS_FULL_REVIEW()
NOT DETERMINISTIC
BEGIN
  START TRANSACTION;

    SET @@sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
    SELECT
       CONCAT(p.firstname, ' ', p.lastname) AS pupil_name,
       p.email AS pupil_email,
       c.name AS class_name,

       s.name AS subject,
       MIN(m.value) AS Mark_MIN,
       MAX(m.value) AS Mark_MAX,
       ROUND(AVG(m.value), 2) AS Mark_AVG,
       COUNT(*) AS Total_marks,
       GROUP_CONCAT(m.value) as Marks,
       CONCAT(t.firstname, ' ', t.lastname) AS teacher_name,
       t.email AS teacher_email,

       IF(AVG(m.value) >= 4, 'Passed', 'Failed') AS Status

    FROM pupils p
    CROSS JOIN (SELECT @cnt := 0) AS dummy
    INNER JOIN classes c ON p.class_id = c.id
    INNER JOIN marks m ON m.pupil_id = p.id
    INNER JOIN subjects s ON m.subject_id = s.id
    INNER JOIN teachers t ON s.teacher_id = t.id
    GROUP BY subject, pupil_name
    ORDER BY pupil_name ;
  COMMIT;

END$$

-- use school;
-- call GET_PUPILS_FULL_REVIEW();


-- ---------------------------------------------------------------------------------------------------------------------
-- -Get pupils list with marks per subject by email (min, max, avg, count, passes or not, class name and class head)----
-- ---------------------------------------------------------------------------------------------------------------------


DROP PROCEDURE IF EXISTS GET_PUPILS_FULL_REVIEW_BY_EMAIL;
CREATE PROCEDURE GET_PUPILS_FULL_REVIEW_BY_EMAIL(IN EMAIL VARCHAR(50))
NOT DETERMINISTIC
BEGIN
  START TRANSACTION;

    SET @@sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
    SELECT
       CONCAT(p.firstname, ' ', p.lastname) AS pupil_name,
       p.email AS pupil_email,
       c.name AS class_name,

       s.name AS subject,
       MIN(m.value) AS Mark_MIN,
       MAX(m.value) AS Mark_MAX,
       ROUND(AVG(m.value), 2) AS Mark_AVG,
       COUNT(*) AS Total_marks,
       GROUP_CONCAT(m.value) as Marks,
       CONCAT(t.firstname, ' ', t.lastname) AS teacher_name,
       t.email AS teacher_email,

       IF(AVG(m.value) >= 4, 'Passed', 'Failed') AS Status

    FROM pupils p
    CROSS JOIN (SELECT @cnt := 0) AS dummy
    INNER JOIN classes c ON p.class_id = c.id
    INNER JOIN marks m ON m.pupil_id = p.id
    INNER JOIN subjects s ON m.subject_id = s.id
    INNER JOIN teachers t ON s.teacher_id = t.id
    WHERE p.email = EMAIL
    GROUP BY subject, pupil_name
    ORDER BY pupil_name ;

  COMMIT;

END$$

-- use school;
-- call GET_PUPILS_FULL_REVIEW_BY_EMAIL('acurmiw@yahoo.com');

DELIMITER ;