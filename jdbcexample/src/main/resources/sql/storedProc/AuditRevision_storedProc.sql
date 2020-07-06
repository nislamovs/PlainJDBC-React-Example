CREATE DATABASE IF NOT EXISTS school ;
use school ;

-- Unfortunatelly You cannot use prepared statements from triggers, and call storedProcedures, that contains prepared statements as well;


DELIMITER ;;

DROP PROCEDURE IF EXISTS ADD_AUDIT_REC;
CREATE PROCEDURE ADD_AUDIT_REC(IN pusername varchar(50), IN pauditaction varchar(10), IN ptablename varchar(50), IN precordid INTEGER,
                               IN pfieldname varchar(50), IN poldvalue longtext, IN pnewvalue longtext)
  BEGIN
  START TRANSACTION;

    SET @auditaction=pauditaction;
    SET @username=pusername;
    SET @tablename=ptablename;
    SET @fieldname=pfieldname;
    SET @oldvalue=poldvalue;
    SET @newvalue=pnewvalue;
    SET @recordid=precordid;

    SET @generic_audit_sql_query = CONCAT('INSERT INTO audit_generic(done_by, auditAction, tableName, recordId, fieldName, oldValue, newValue)
                                           VALUES ("', @username, '", "', @auditaction, '", "', @tablename, '", "', @recordid, '", "', @fieldname, '", "', @oldvalue, '", "', @newvalue, '");');

    SET @table_audit_sql_query = CONCAT('INSERT INTO ', 'audit_', @tablename, '(done_by, auditAction, recordId, fieldName, oldValue, newValue)
                                           VALUES ("', @username, '", "', @auditaction, '", "', @recordid, '", "',  @fieldname, '", "', @oldvalue, '", "', @newvalue, '");');

    PREPARE stmt FROM @generic_audit_sql_query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;

    PREPARE stmt FROM @table_audit_sql_query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;

  COMMIT;
  END;
;;

DELIMITER ;