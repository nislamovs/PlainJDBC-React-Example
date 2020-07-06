DELIMITER $$

CREATE TRIGGER teachers_insert_trigger AFTER INSERT ON teachers
FOR EACH ROW
    BEGIN
        SET @recordid = NEW.id;
        SET @auditaction='INSERT';
        SET @username=SESSION_USER();
        SET @tablename='teachers';
        SET @oldvalue='<EMPTY>';

        SET @fieldlist=(SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = @tablename);
        SET @newvalue=(CONCAT('<',
        ' ', NEW.id,
        ' ', NEW.firstname,
        ' ', NEW.lastname,
        ' ', NEW.email,
        ' ', NEW.birthdate,
        ' ', NEW.class_id,
        ' ', NEW.subject_id,
        ' ', NEW.is_head,
        ' ', NEW.created_at,
        ' ', NEW.created_by,
        ' ', NEW.modified_at,
        ' ', NEW.modified_by,
        ' ', '>'));

        INSERT INTO audit_generic(done_by, auditAction, tableName, recordId, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @tablename, @recordid, @fieldlist, @oldvalue, @newvalue);

        INSERT INTO audit_teachers(done_by, auditAction, recordId, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
    END;
$$

CREATE TRIGGER teachers_update_trigger AFTER UPDATE ON teachers
FOR EACH ROW
    BEGIN
        SET @recordid = NEW.id;
        SET @auditaction='UPDATE';
        SET @username=SESSION_USER();
        SET @tablename='teachers';

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

        IF (NEW.birthdate <> OLD.birthdate) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'birthdate'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.birthdate));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.birthdate));
        END IF;

        IF (NEW.class_id <> OLD.class_id) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'class_id'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.class_id));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.class_id));
        END IF;

        IF (NEW.subject_id <> OLD.subject_id) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'subject_id'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.subject_id));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.subject_id));
        END IF;

        IF (NEW.is_head <> OLD.is_head) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'is_head'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.is_head));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.is_head));
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

            INSERT INTO audit_teachers(done_by, auditAction, recordid, fieldName, oldValue, newValue)
            VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
        END IF;
    END;
$$

CREATE TRIGGER teachers_delete_trigger BEFORE DELETE ON teachers
FOR EACH ROW
    BEGIN
        SET @recordid = OLD.id;
        SET @auditaction='DELETE';
        SET @username=SESSION_USER();
        SET @tablename='teachers';
        SET @newvalue='<EMPTY>';

        SET @fieldlist=(SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = @tablename);
        SET @oldvalue=(CONCAT('<',
        ' ', OLD.id,
        ' ', OLD.firstname,
        ' ', OLD.lastname,
        ' ', OLD.email,
        ' ', OLD.birthdate,
        ' ', IFNULL(OLD.class_id, '<NULL>'),
        ' ', OLD.subject_id,
        ' ', OLD.is_head,
        ' ', OLD.created_at,
        ' ', OLD.created_by,
        ' ', OLD.modified_at,
        ' ', OLD.modified_by,
        ' ', '>'));

        INSERT INTO audit_generic(done_by, auditAction, tableName, recordId, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @tablename, @recordid, @fieldlist, @oldvalue, @newvalue);

        INSERT INTO audit_teachers(done_by, auditAction, recordId, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
    END;
$$

DELIMITER ;