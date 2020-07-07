DELIMITER $$

CREATE TRIGGER teachers_validation_insert_trigger BEFORE INSERT ON teachers
FOR EACH ROW
    BEGIN
      IF (NEW.email NOT REGEXP '^[a-zA-Z0-9][+a-zA-Z0-9._-]*@[a-zA-Z0-9][a-zA-Z0-9._-]*[a-zA-Z0-9]*\\.[a-zA-Z]{2,4}$') THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Teacher`s email does not fit into email regex pattern';
      END IF;

      IF (NEW.birthdate > NOW()) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Teacher`s birthdate cannot be put in future';
      END IF;

      IF ((TIMESTAMPDIFF(YEAR, NEW.birthdate, CURDATE()) > 65) || (TIMESTAMPDIFF(YEAR, NEW.birthdate, CURDATE()) < 21)) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Teacher`s age does not fit into interval: [21-65] years';
      END IF;
    END;
$$

CREATE TRIGGER teachers_validation_update_trigger BEFORE UPDATE ON teachers
FOR EACH ROW
    BEGIN
      IF OLD.email <> NEW.email && (NEW.email NOT REGEXP '^[a-zA-Z0-9][+a-zA-Z0-9._-]*@[a-zA-Z0-9][a-zA-Z0-9._-]*[a-zA-Z0-9]*\\.[a-zA-Z]{2,4}$') THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Teacher`s email does not fit into email regex pattern';
      END IF;

      IF OLD.birthdate <> NEW.birthdate && (NEW.birthdate > NOW()) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Teacher`s birthdate cannot be put in future';
      END IF;

      IF ((TIMESTAMPDIFF(YEAR, NEW.birthdate, CURDATE()) > 65) || (TIMESTAMPDIFF(YEAR, NEW.birthdate, CURDATE()) < 21)) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Teacher`s age does not fit into interval: [21-65] years';
      END IF;
    END;
$$

DELIMITER ;