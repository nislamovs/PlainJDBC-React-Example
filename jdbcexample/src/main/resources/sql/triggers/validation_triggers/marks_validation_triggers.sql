DELIMITER $$

CREATE TRIGGER marks_validation_insert_trigger BEFORE INSERT ON marks
FOR EACH ROW
    BEGIN
      IF (NEW.value < 1 OR NEW.value > 10) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Mark value does not fit into interval [1-10]';
      END IF;

      IF NEW.date > NOW() THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Mark value cannot be put in future';
      END IF;
    END;
$$

CREATE TRIGGER marks_validation_update_trigger BEFORE UPDATE ON marks
FOR EACH ROW
    BEGIN
      IF OLD.value <> NEW.value && (NEW.value < 1 OR NEW.value > 10) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Mark value does not fit into interval [1-10]';
      END IF;

      IF OLD.date <> NEW.date && (NEW.date > NOW()) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Mark value cannot be put in future';
      END IF;
    END;
$$


DELIMITER ;