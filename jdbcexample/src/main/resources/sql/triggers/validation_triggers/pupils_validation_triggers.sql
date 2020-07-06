DELIMITER $$

CREATE TRIGGER pupils_validation_insert_trigger BEFORE INSERT ON pupils
FOR EACH ROW
    BEGIN

    END;
$$

CREATE TRIGGER pupils_validation_update_trigger BEFORE UPDATE ON pupils
FOR EACH ROW
    BEGIN

    END;
$$

DELIMITER ;