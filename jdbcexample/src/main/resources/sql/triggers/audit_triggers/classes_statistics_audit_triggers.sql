DELIMITER $$

CREATE TRIGGER classes_statistics_insert_trigger AFTER INSERT ON classes_statistics
FOR EACH ROW
    BEGIN
        SET @recordid = NEW.id;
        SET @auditaction='INSERT';
        SET @username=SESSION_USER();
        SET @tablename='classes_statistics';
        SET @oldvalue='<EMPTY>';

        SET @fieldlist=(SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = @tablename);
        SET @newvalue=(CONCAT('<',
        ' ', NEW.id,
        ' ', NEW.class_id,
        ' ', NEW.class_name,
        ' ', NEW.created,
        ' ', NEW.pupil_count,
        ' ', NEW.created_at,
        ' ', NEW.created_by,
        ' ', NEW.modified_at,
        ' ', NEW.modified_by,
        ' ', '>'));

        INSERT INTO audit_generic(done_by, auditAction, tableName, recordId, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @tablename, @recordid, @fieldlist, @oldvalue, @newvalue);

        INSERT INTO audit_classes_statistics(done_by, auditAction, recordid, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
    END;
$$

CREATE TRIGGER classes_statistics_update_trigger AFTER UPDATE ON classes_statistics
FOR EACH ROW
    BEGIN
        SET @recordid = NEW.id;
        SET @auditaction='UPDATE';
        SET @username=SESSION_USER();
        SET @tablename='classes_statistics';

        SET @fieldlist=' ';
        SET @oldvalue=' ';
        SET @newvalue=' ';

        IF (NEW.class_id <> OLD.class_id) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'class_id'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.class_id));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.class_id));
        END IF;

        IF (NEW.class_name <> OLD.class_name) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'class_name'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.class_name));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.class_name));
        END IF;

        IF (NEW.created <> OLD.created) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'created'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.created));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.created));
        END IF;

        IF (NEW.pupil_count <> OLD.pupil_count) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'pupil_count'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.pupil_count));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.pupil_count));
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

            INSERT INTO audit_classes_statistics(done_by, auditAction, recordid, fieldName, oldValue, newValue)
            VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
        END IF;
    END;
$$

CREATE TRIGGER classes_statistics_delete_trigger BEFORE DELETE ON classes_statistics
FOR EACH ROW
    BEGIN
        SET @recordid = OLD.id;
        SET @auditaction='DELETE';
        SET @username=SESSION_USER();
        SET @tablename='classes_statistics';
        SET @newvalue='<EMPTY>';

        SET @fieldlist=(SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = @tablename);
        SET @oldvalue=(CONCAT('<',
        ' ', OLD.id,
        ' ', OLD.class_id,
        ' ', OLD.class_name,
        ' ', OLD.created,
        ' ', OLD.pupil_count,
        ' ', OLD.created_at,
        ' ', OLD.created_by,
        ' ', OLD.modified_at,
        ' ', OLD.modified_by,
        ' ', '>'));

        INSERT INTO audit_generic(done_by, auditAction, tableName, recordId, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @tablename, @recordid, @fieldlist, @oldvalue, @newvalue);

        INSERT INTO audit_classes_statistics(done_by, auditAction, recordid, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
    END;
$$

DELIMITER ;