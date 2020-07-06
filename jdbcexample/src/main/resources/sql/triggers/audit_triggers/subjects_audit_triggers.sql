DELIMITER $$

CREATE TRIGGER subjects_insert_trigger AFTER INSERT ON subjects
FOR EACH ROW
    BEGIN
        SET @recordid = NEW.id;
        SET @auditaction='INSERT';
        SET @username=SESSION_USER();
        SET @tablename='subjects';
        SET @oldvalue='<EMPTY>';

        SET @fieldlist=(SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = @tablename);
        SET @newvalue=(CONCAT('<',
        ' ', NEW.id,
        ' ', NEW.teacher_id,
        ' ', NEW.name,
        ' ', NEW.created_at,
        ' ', NEW.created_by,
        ' ', NEW.modified_at,
        ' ', NEW.modified_by,
        ' ', '>'));

        INSERT INTO audit_generic(done_by, auditAction, tableName, recordId, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @tablename, @recordid, @fieldlist, @oldvalue, @newvalue);

        INSERT INTO audit_subjects(done_by, auditAction, recordid, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
    END;
$$

CREATE TRIGGER subjects_update_trigger AFTER UPDATE ON subjects
FOR EACH ROW
    BEGIN
        SET @recordid = NEW.id;
        SET @auditaction='UPDATE';
        SET @username=SESSION_USER();
        SET @tablename='subjects';

        SET @fieldlist=' ';
        SET @oldvalue=' ';
        SET @newvalue=' ';

        IF (NEW.teacher_id <> OLD.teacher_id) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'teacher_id'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.teacher_id));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.teacher_id));
        END IF;

        IF (NEW.name <> OLD.name) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'name'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.name));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.name));
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

            INSERT INTO audit_subjects(done_by, auditAction, recordid, fieldName, oldValue, newValue)
            VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
        END IF;
    END;
$$

CREATE TRIGGER subjects_delete_trigger BEFORE DELETE ON subjects
FOR EACH ROW
    BEGIN
        SET @recordid = OLD.id;
        SET @auditaction='DELETE';
        SET @username=SESSION_USER();
        SET @tablename='subjects';
        SET @newvalue='<EMPTY>';

        SET @fieldlist=(SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = @tablename);
        SET @oldvalue=(CONCAT('<',
        ' ', OLD.id,
        ' ', OLD.teacher_id,
        ' ', OLD.name,
        ' ', OLD.created_at,
        ' ', OLD.created_by,
        ' ', OLD.modified_at,
        ' ', OLD.modified_by,
        ' ', '>'));

        INSERT INTO audit_generic(done_by, auditAction, tableName, recordId, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @tablename, @recordid, @fieldlist, @oldvalue, @newvalue);

        INSERT INTO audit_subjects(done_by, auditAction, recordid, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
    END;
$$

DELIMITER ;