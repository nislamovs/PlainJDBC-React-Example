CREATE DATABASE IF NOT EXISTS school ;
use school ;

DELIMITER ;;

DROP PROCEDURE IF EXISTS CREATE_CLASS;
CREATE PROCEDURE CREATE_CLASS()
  BEGIN

    SET @id = (select id from classes ORDER BY id DESC LIMIT 1) + 1;
    SET @type = GET_RANDOM_CLASS_TYPE();
    SET @class_head_id = (
                             select id from teachers
                             WHERE class_id IS NOT NULL
                             AND isHead IS TRUE
                             ORDER BY id DESC LIMIT 1
                         ) + 1;
    SET @name = GET_RANDOM_CLASS_NAME();


    insert into classes (id, type, class_head_id, name)
    values (@id, @type, @class_head_id, @name);
  END;
;;

DROP PROCEDURE IF EXISTS CREATE_TEACHER;
CREATE PROCEDURE CREATE_TEACHER(IN isHead BOOLEAN)
BEGIN

    SET @id = (select id from teachers ORDER BY id DESC LIMIT 1) + 1;
    SET @firstname = GET_RANDOM_FIRSTNAME();
    SET @lastname = GET_RANDOM_LASTNAME();
    SET @email = GET_EMAIL(@firstname, @lastname);
    SET @birthdate = GET_RANDOM_BIRTHDATE(28, 56);
    SET @class_id = (
                        select class_id from teachers
                        where class_id IS NOT NULL
                        AND isHead IS TRUE
                        ORDER BY id DESC LIMIT 1
                    ) + 1;
    SET @subject_id = (SELECT FLOOR(1 + (RAND() * (select COUNT(*) FROM subjects))) );
    SET @isHead = isHead;

    insert into teachers (id, firstname, lastname, email, birthdate, class_id, subject_id, isHead)
    values (@id, @firstname, @lastname, @email, @birthdate, @class_id, @subject_id, @isHead);
END;
;;

DROP PROCEDURE IF EXISTS CREATE_PUPILS_FOR_CLASS;
CREATE PROCEDURE CREATE_PUPILS_FOR_CLASS(IN pupilCount INTEGER)
BEGIN

    SET @class_id = (
        select id from teachers
        where class_id IS NOT NULL
          AND isHead IS TRUE
        ORDER BY id DESC LIMIT 1
    );
    SET @class_head_id = (
        select class_id from teachers
        where class_id IS NOT NULL
          AND isHead IS TRUE
        ORDER BY id DESC LIMIT 1
    );

    SET @OFFSET = (SELECT id FROM pupils ORDER BY id DESC LIMIT 1);
    SET @i = @OFFSET + 1;
    WHILE @i < @OFFSET+pupilCount+1 DO
            SET @pupil_firstname = GET_RANDOM_FIRSTNAME();
            SET @pupil_lastname = GET_RANDOM_LASTNAME();
            SET @email = GET_EMAIL(@pupil_firstname, @pupil_lastname);
            SET @pupil_gender = GET_RANDOM_GENDER();
            SET @pupil_birthdate = GET_RANDOM_BIRTHDATE(7, 18);

            insert into pupils (id, firstname, lastname, email, gender, birthdate, class_id, class_head_id)
            values (@i, @pupil_firstname, @pupil_lastname, @email, @pupil_gender,  @pupil_birthdate, @class_id, @class_head_id);

            SET @i = @i + 1;
        END WHILE;

END;
;;

DROP PROCEDURE IF EXISTS CREATE_MARKS_FOR_PUPILS;
CREATE PROCEDURE CREATE_MARKS_FOR_PUPILS(IN pupilCount INTEGER)
BEGIN

    SET @OFFSET = (SELECT id FROM marks ORDER BY id DESC LIMIT 1);
    SET @id = @OFFSET;
    SET @pupil_id_start = (SELECT id FROM pupils ORDER BY id DESC LIMIT 1) - pupilCount;
    SET @pupil_id_end = @pupil_id_start + pupilCount;
    SET @pupil_id = @pupil_id_start;

    WHILE @pupil_id < @pupil_id_end+1 DO
        SET @pupil_id = @pupil_id + 1;
        SET @marks_count = (SELECT (FLOOR( 1 + RAND( ) * 4 )) );
        WHILE @marks_count > 0 DO
                SET @marks_count = @marks_count - 1;

                SET @id = @id + 1;
                SET @subject_id = GET_RANDOM_SUBJECT_ID();

                SET @date =  GET_RANDOM_MARK_DATE(100);
                SET @value = (SELECT (FLOOR( 1 + RAND( ) * 10 )) );

                insert into marks (id, subject_id, pupil_id, date, value)
                values (@id, @subject_id, @pupil_id, @date, @value);
        END WHILE;
    END WHILE;
END;
;;

DROP PROCEDURE IF EXISTS GENERATE_NEW_CLASS;
CREATE PROCEDURE GENERATE_NEW_CLASS(IN pupilCount INTEGER)
BEGIN
    CALL CREATE_CLASS();
    CALL CREATE_TEACHER(true);
    CALL CREATE_PUPILS_FOR_CLASS(pupilCount);
    CALL CREATE_MARKS_FOR_PUPILS(pupilCount);
END;
;;

DELIMITER ;