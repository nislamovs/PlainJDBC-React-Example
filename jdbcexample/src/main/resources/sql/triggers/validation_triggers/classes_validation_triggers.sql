DELIMITER $$

CREATE TRIGGER classes_validation_insert_trigger BEFORE INSERT ON classes
FOR EACH ROW
    BEGIN

    END;
$$

CREATE TRIGGER classes_validation_update_trigger BEFORE UPDATE ON classes
FOR EACH ROW
    BEGIN

    END;
$$

DELIMITER ;


-- CREATE TABLE data (
--   phone varchar(100)
-- );
--
-- DELIMITER $$
-- CREATE TRIGGER trig_phone_check BEFORE INSERT ON data
-- FOR EACH ROW
-- BEGIN
-- IF (NEW.phone REGEXP '^(\\+?[0-9]{1,4}-)?[0-9]{3,10}$' ) = 0 THEN
--   SIGNAL SQLSTATE '12345'
--      SET MESSAGE_TEXT = 'Wroooong!!!';
-- END IF;
-- END$$
-- DELIMITER ;
--
--
-- INSERT INTO data VALUES ('+64-221221442'); -- should be OK
-- INSERT INTO data VALUES ('+64-22122 WRONG 1442'); -- will fail with the error: #1644 - Wroooong!!!