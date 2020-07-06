DELIMITER $$

CREATE TRIGGER classes_insert_trigger AFTER INSERT ON classes
FOR EACH ROW
    BEGIN
        SET @recordid = NEW.id;
        SET @auditaction='INSERT';
        SET @username=SESSION_USER();
        SET @tablename='classes';
        SET @oldvalue='<EMPTY>';

        SET @fieldlist=(SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = @tablename);
        SET @newvalue=(CONCAT('<',
        ' ', NEW.id,
        ' ', NEW.type,
        ' ', NEW.class_head_id,
        ' ', NEW.name,
        ' ', NEW.created_at,
        ' ', NEW.created_by,
        ' ', NEW.modified_at,
        ' ', NEW.modified_by,
        ' ', '>'));

        INSERT INTO audit_generic(done_by, auditAction, tableName, recordId, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @tablename, @recordid, @fieldlist, @oldvalue, @newvalue);

        INSERT INTO audit_classes(done_by, auditAction, recordid, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
    END;
$$

CREATE TRIGGER classes_update_trigger AFTER UPDATE ON classes
FOR EACH ROW
    BEGIN
        SET @recordid = NEW.id;
        SET @auditaction='UPDATE';
        SET @username=SESSION_USER();
        SET @tablename='classes';

        SET @fieldlist=' ';
        SET @oldvalue=' ';
        SET @newvalue=' ';

        IF (NEW.type <> OLD.type) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'type'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.type));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.type));
        END IF;

        IF (NEW.class_head_id <> OLD.class_head_id) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'class_head_id'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.class_head_id));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.class_head_id));
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

            INSERT INTO audit_classes(done_by, auditAction, recordid, fieldName, oldValue, newValue)
            VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
        END IF;
    END;
$$

CREATE TRIGGER classes_delete_trigger BEFORE DELETE ON classes
FOR EACH ROW
    BEGIN
        SET @recordid = OLD.id;
        SET @auditaction='DELETE';
        SET @username=SESSION_USER();
        SET @tablename='classes';
        SET @newvalue='<DELETED>';

        SET @fieldlist=(SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = @tablename);
        SET @oldvalue=(CONCAT('<',
        ' ', OLD.id,
        ' ', OLD.type,
        ' ', OLD.class_head_id,
        ' ', OLD.name,
        ' ', OLD.created_at,
        ' ', OLD.created_by,
        ' ', OLD.modified_at,
        ' ', OLD.modified_by,
        ' ', '>'));

        INSERT INTO audit_generic(done_by, auditAction, tableName, recordId, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @tablename, @recordid, @fieldlist, @oldvalue, @newvalue);

        INSERT INTO audit_classes(done_by, auditAction, recordid, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
    END;
$$

DELIMITER ;