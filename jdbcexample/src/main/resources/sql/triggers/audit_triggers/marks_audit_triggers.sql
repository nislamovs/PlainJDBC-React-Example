DELIMITER $$

CREATE TRIGGER marks_insert_trigger AFTER INSERT ON marks
FOR EACH ROW
    BEGIN
        SET @recordid = NEW.id;
        SET @auditaction='INSERT';
        SET @username=SESSION_USER();
        SET @tablename='marks';
        SET @oldvalue='<EMPTY>';

        SET @fieldlist=(SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = @tablename);
        SET @newvalue=(CONCAT('<',
        ' ', NEW.id,
        ' ', NEW.subject_id,
        ' ', NEW.pupil_id,
        ' ', NEW.date,
        ' ', NEW.value,
        ' ', NEW.created_at,
        ' ', NEW.created_by,
        ' ', NEW.modified_at,
        ' ', NEW.modified_by,
        ' ', '>'));

        INSERT INTO audit_generic(done_by, auditAction, tableName, recordId, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @tablename, @recordid, @fieldlist, @oldvalue, @newvalue);

        INSERT INTO audit_marks(done_by, auditAction, recordid, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
    END;
$$

CREATE TRIGGER marks_update_trigger AFTER UPDATE ON marks
FOR EACH ROW
    BEGIN
        SET @recordid = NEW.id;
        SET @auditaction='UPDATE';
        SET @username=SESSION_USER();
        SET @tablename='marks';

        SET @fieldlist=' ';
        SET @oldvalue=' ';
        SET @newvalue=' ';

        IF (NEW.subject_id <> OLD.subject_id) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'subject_id'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.subject_id));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.subject_id));
        END IF;

        IF (NEW.pupil_id <> OLD.pupil_id) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'pupil_id'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.pupil_id));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.pupil_id));
        END IF;

        IF (NEW.date <> OLD.date) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'date'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.date));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.date));
        END IF;

        IF (NEW.value <> OLD.value) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'value'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.value));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.value));
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

            INSERT INTO audit_marks(done_by, auditAction, recordid, fieldName, oldValue, newValue)
            VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
        END IF;
    END;
$$

CREATE TRIGGER marks_delete_trigger BEFORE DELETE ON marks
FOR EACH ROW
    BEGIN
        SET @recordid = OLD.id;
        SET @auditaction='DELETE';
        SET @username=SESSION_USER();
        SET @tablename='marks';
        SET @onewvalue='<EMPTY>';

        SET @fieldlist=(SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = @tablename);
        SET @oldvalue=(CONCAT('<',
        ' ', OLD.id,
        ' ', OLD.subject_id,
        ' ', OLD.pupil_id,
        ' ', OLD.date,
        ' ', OLD.value,
        ' ', OLD.created_at,
        ' ', OLD.created_by,
        ' ', OLD.modified_at,
        ' ', OLD.modified_by,
        ' ', '>'));

        INSERT INTO audit_generic(done_by, auditAction, tableName, recordId, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @tablename, @recordid, @fieldlist, @oldvalue, @newvalue);

        INSERT INTO audit_marks(done_by, auditAction, recordid, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
    END;
$$

DELIMITER ;