DELIMITER $$

CREATE TRIGGER pupils_insert_trigger AFTER INSERT ON pupils
FOR EACH ROW
    BEGIN
        SET @recordid = NEW.id;
        SET @auditaction='INSERT';
        SET @username=SESSION_USER();
        SET @tablename='pupils';
        SET @oldvalue='<EMPTY>';

        SET @fieldlist=(SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = @tablename);
        SET @newvalue=(CONCAT('<',
        ' ', NEW.id,
        ' ', NEW.firstname,
        ' ', NEW.lastname,
        ' ', NEW.email,
        ' ', NEW.gender,
        ' ', NEW.birthdate,
        ' ', NEW.class_id,
        ' ', NEW.class_head_id,
        ' ', NEW.created_at,
        ' ', NEW.created_by,
        ' ', NEW.modified_at,
        ' ', NEW.modified_by,
        ' ', '>'));

        INSERT INTO audit_generic(done_by, auditAction, tableName, recordId, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @tablename, @recordid, @fieldlist, @oldvalue, @newvalue);

        INSERT INTO audit_pupils(done_by, auditAction, recordid, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
    END;
$$

CREATE TRIGGER pupils_update_trigger AFTER UPDATE ON pupils
FOR EACH ROW
    BEGIN
        SET @recordid = NEW.id;
        SET @auditaction='UPDATE';
        SET @username=SESSION_USER();
        SET @tablename='pupils';

        SET @fieldlist=' ';
        SET @oldvalue=' ';
        SET @newvalue=' ';

        IF (NEW.firstname <> OLD.firstname) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'firstname'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.firstname));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.firstname));
        END IF;

        IF (NEW.lastname <> OLD.lastname) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'lastname'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.lastname));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.lastname));
        END IF;

        IF (NEW.email <> OLD.email) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'email'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.email));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.email));
        END IF;

        IF (NEW.gender <> OLD.gender) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'gender'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.gender));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.gender));
        END IF;

        IF (NEW.lastname <> OLD.lastname) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'lastname'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.lastname));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.lastname));
        END IF;

        IF (NEW.class_id <> OLD.class_id) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'class_id'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.class_id));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.class_id));
        END IF;

        IF (NEW.class_head_id <> OLD.class_head_id) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'class_head_id'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.class_head_id));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.class_head_id));
        END IF;

        IF (@oldvalue <> @newvalue) THEN
            -- Delete space and comma from the beginning
            SET @oldvalue=(REPLACE(@oldvalue,' ,',''));
            SET @newvalue=(REPLACE(@newvalue,' ,',''));
            SET @fieldlist=(REPLACE(@fieldlist,' ,',''));

            SET @oldvalue=(CONCAT('<', @oldvalue, '>'));
            SET @newvalue=(CONCAT('<', @newvalue, '>'));

            INSERT INTO audit_generic(done_by, auditAction, tableName, recordId, fieldName, oldValue, newValue)
            VALUES (@username, @auditaction, @tablename, @recordid, @fieldlist, @oldvalue, @newvalue);

            INSERT INTO audit_pupils(done_by, auditAction, recordid, fieldName, oldValue, newValue)
            VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
        END IF;
    END;
$$

CREATE TRIGGER pupils_delete_trigger BEFORE DELETE ON pupils
FOR EACH ROW
    BEGIN
        SET @recordid = OLD.id;
        SET @auditaction='DELETE';
        SET @username=SESSION_USER();
        SET @tablename='pupils';
        SET @newvalue='<EMPTY>';

        SET @fieldlist=(SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = @tablename);
        SET @oldvalue=(CONCAT('<',
        ' ', OLD.id,
        ' ', OLD.firstname,
        ' ', OLD.lastname,
        ' ', OLD.email,
        ' ', OLD.gender,
        ' ', OLD.birthdate,
        ' ', OLD.class_id,
        ' ', OLD.class_head_id,
        ' ', OLD.created_at,
        ' ', OLD.created_by,
        ' ', OLD.modified_at,
        ' ', OLD.modified_by,
        ' ', '>'));

        INSERT INTO audit_generic(done_by, auditAction, tableName, recordId, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @tablename, @recordid, @fieldlist, @oldvalue, @newvalue);

        INSERT INTO audit_pupils(done_by, auditAction, recordid, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
    END;
$$

DELIMITER ;