DELIMITER $$

CREATE TRIGGER teachers_validation_insert_trigger BEFORE INSERT ON teachers
FOR EACH ROW
    BEGIN

    END;
$$

CREATE TRIGGER teachers_validation_update_trigger BEFORE UPDATE ON teachers
FOR EACH ROW
    BEGIN

    END;
$$

DELIMITER ;