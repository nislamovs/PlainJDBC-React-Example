DELIMITER $$

CREATE TRIGGER classes_statistics_validation_insert_trigger BEFORE INSERT ON classes_statistics
FOR EACH ROW
    BEGIN
        IF (NEW.created > NOW()) THEN
            SIGNAL SQLSTATE '45000'
                SET MESSAGE_TEXT = 'School class creation date cannot be in future!';
        END IF;
    END;
$$

CREATE TRIGGER classes_statistics_validation_update_trigger BEFORE UPDATE ON classes_statistics
FOR EACH ROW
    BEGIN
        IF (OLD.created <> NEW.created && NEW.created > NOW()) THEN
            SIGNAL SQLSTATE '45000'
                SET MESSAGE_TEXT = 'School class creation date cannot be in future!';
        END IF;
    END;
$$

DELIMITER ;