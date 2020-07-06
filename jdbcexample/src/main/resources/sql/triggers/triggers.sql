CREATE DATABASE IF NOT EXISTS school ;
use school ;

-- DELIMITER $$
--
-- CREATE TRIGGER new_class_stats AFTER UPDATE ON pupils
-- FOR EACH ROW
--     BEGIN
--
-- -- 	id INTEGER AUTO_INCREMENT PRIMARY KEY,
-- --     auditAction ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,
-- --
-- --     fieldName VARCHAR(50) NOT NULL,
-- --     oldValue longtext,
-- --     newValue longtext,
-- --
-- -- 	done_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
-- --  	done_by VARCHAR(50) DEFAULT "Admin" NOT NULL
--
--
--
--         insert into audit_marks(auditAction,fieldName, oldValue,newValue, done_by ) values ('UPDATE', 'asdasd', OLD.id, 'asdasd', session_user());
--
--     END;
-- $$






-- CREATE TRIGGER new_class_stats AFTER INSERT ON pupils
-- FOR EACH ROW
--     BEGIN
--         SET @class_id = 0;
--         SET @class_name = '';
--         SET @date = (SELECT now());
--         SET @pupil_count = 0;
--
--         SELECT
--          c.id AS `Id`,
--          c.name AS `Class name`,
--          c.type AS `Class type`,
--         (SELECT CONCAT(t.firstname, ' ', t.lastname) AS class_head_name FROM teachers AS t WHERE t.id=c.class_head_id) AS `Class head name`,
--         (SELECT count(*) FROM pupils AS p WHERE c.id=p.class_id) AS `Pupil count`
--         FROM classes AS c
--
--     END;
-- $$

DELIMITER ;


--
-- DELIMITER $$
--
-- CREATE TRIGGER new_class_stats AFTER INSERT ON classes
--     BEGIN
--         SET @class_id = 0;
--
--     END;
-- $$
--
-- DELIMITER ;


--
-- DELIMITER $$
--
-- DROP TRIGGER IF EXISTS pupil_created;
-- CREATE TRIGGER pupil_created BEFORE INSERT ON pupils
-- FOR EACH ROW
--     BEGIN
--        IF    NEW.id <> OLD.id
--           || NEW.firstname <> OLD.firstname
--           || NEW.lastname <> OLD.lastname
--           || NEW.email <> OLD.email
--           || NEW.gender <> OLD.gender
--           || NEW.birthdate <> OLD.birthdate
--           || NEW.class_id <> OLD.class_id
--           || NEW.class_head_id <> OLD.class_head_id
--           || NEW.birthdate <> OLD.birthdate
--           || NEW.modifiedDate <> OLD.modifiedDate
--        THEN
--             SET NEW.createdDate = now()
--             SET NEW.modifiedDate = now();
--        END IF;
--     END;
-- $$
--
-- DELIMITER ;