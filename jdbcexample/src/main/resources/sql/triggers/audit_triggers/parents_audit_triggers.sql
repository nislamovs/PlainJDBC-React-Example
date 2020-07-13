DELIMITER $$

CREATE TRIGGER parents_insert_trigger AFTER INSERT ON parents
FOR EACH ROW
    BEGIN
        SET @recordid = NEW.id;
        SET @auditaction='INSERT';
        SET @username=SESSION_USER();
        SET @tablename='parents';
        SET @oldvalue='<EMPTY>';

        SET @fieldlist=(SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = @tablename);
        SET @newvalue=(CONCAT('<',
        ' ', NEW.id,
        ' ', NEW.firstname,
        ' ', NEW.lastname,
        ' ', NEW.email,
        ' ', NEW.parentType,
        ' ', NEW.parentInfo,
        ' ', NEW.birthdate,
        ' ', NEW.address,
        ' ', NEW.phonenumber,
        ' ', NEW.familyId,
        ' ', NEW.created_at,
        ' ', NEW.created_by,
        ' ', NEW.modified_at,
        ' ', NEW.modified_by,
        ' ', '>'));

        INSERT INTO audit_generic(done_by, auditAction, tableName, recordId, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @tablename, @recordid, @fieldlist, @oldvalue, @newvalue);

        INSERT INTO audit_parents(done_by, auditAction, recordid, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
    END;
$$

CREATE TRIGGER parents_update_trigger AFTER UPDATE ON parents
FOR EACH ROW
    BEGIN
        SET @recordid = NEW.id;
        SET @auditaction='UPDATE';
        SET @username=SESSION_USER();
        SET @tablename='parents';

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

        IF (NEW.parentType <> OLD.parentType) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'parentType'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.parentType));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.parentType));
        END IF;

        IF (NEW.parentInfo <> OLD.parentInfo) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'parentInfo'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.parentInfo));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.parentInfo));
        END IF;

        IF (NEW.birthdate <> OLD.birthdate) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'birthdate'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.birthdate));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.birthdate));
        END IF;

        IF (NEW.address <> OLD.address) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'address'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.address));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.address));
        END IF;

        IF (NEW.phonenumber <> OLD.phonenumber) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'phonenumber'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.phonenumber));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.phonenumber));
        END IF;

        IF (NEW.familyId <> OLD.familyId) THEN
            SET @fieldlist=(CONCAT(@fieldlist, ',', 'familyId'));
            SET @oldvalue=(CONCAT(@oldvalue, ',', OLD.familyId));
            SET @newvalue=(CONCAT(@newvalue, ',', NEW.familyId));
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

            INSERT INTO audit_parents(done_by, auditAction, recordid, fieldName, oldValue, newValue)
            VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
        END IF;
    END;
$$

CREATE TRIGGER parents_delete_trigger BEFORE DELETE ON parents
FOR EACH ROW
    BEGIN
        SET @recordid = OLD.id;
        SET @auditaction='DELETE';
        SET @username=SESSION_USER();
        SET @tablename='parents';
        SET @newvalue='<EMPTY>';

        SET @fieldlist=(SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = @tablename);
        SET @oldvalue=(CONCAT('<',
        ' ', OLD.id,
        ' ', OLD.firstname,
        ' ', OLD.lastname,
        ' ', OLD.email,
        ' ', OLD.parentType,
        ' ', OLD.parentInfo,
        ' ', OLD.birthdate,
        ' ', OLD.address,
        ' ', OLD.phonenumber,
        ' ', OLD.familyId,
        ' ', OLD.created_at,
        ' ', OLD.created_by,
        ' ', OLD.modified_at,
        ' ', OLD.modified_by,
        ' ', '>'));

        INSERT INTO audit_generic(done_by, auditAction, tableName, recordId, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @tablename, @recordid, @fieldlist, @oldvalue, @newvalue);

        INSERT INTO audit_parents(done_by, auditAction, recordid, fieldName, oldValue, newValue)
        VALUES (@username, @auditaction, @recordid, @fieldlist, @oldvalue, @newvalue);
    END;
$$

DELIMITER ;