DELIMITER $$

CREATE TRIGGER parents_validation_insert_trigger BEFORE INSERT ON parents
FOR EACH ROW
    BEGIN
      IF (NEW.email NOT REGEXP '^[a-zA-Z0-9][+a-zA-Z0-9._-]*@[a-zA-Z0-9][a-zA-Z0-9._-]*[a-zA-Z0-9]*\\.[a-zA-Z]{2,4}$') THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Parent`s email does not fit into email regex pattern';
      END IF;

      IF (NEW.birthdate > NOW()) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Parent`s birthdate cannot be put in future';
      END IF;

      IF (TIMESTAMPDIFF(YEAR, NEW.birthdate, CURDATE()) < 28) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Parent`s age does not fit into interval: [28+] years';
      END IF;
    END;
$$

CREATE TRIGGER parents_validation_update_trigger BEFORE UPDATE ON parents
FOR EACH ROW
    BEGIN
      IF OLD.email <> NEW.email && (NEW.email NOT REGEXP '^[a-zA-Z0-9][+a-zA-Z0-9._-]*@[a-zA-Z0-9][a-zA-Z0-9._-]*[a-zA-Z0-9]*\\.[a-zA-Z]{2,4}$') THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Parent`s email does not fit into email regex pattern';
      END IF;

      IF OLD.birthdate <> NEW.birthdate && (NEW.birthdate > NOW()) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Parent`s birthdate cannot be put in future';
      END IF;

      IF (TIMESTAMPDIFF(YEAR, NEW.birthdate, CURDATE()) < 28) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Parent`s age does not fit into interval: [28+] years';
      END IF;
    END;
$$

DELIMITER ;