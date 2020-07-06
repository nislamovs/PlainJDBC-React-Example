-- Here will be displayed all changes of all db

CREATE DATABASE IF NOT EXISTS school ;
use school ;

CREATE TABLE IF NOT EXISTS audit_generic (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
    auditAction ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,

    tableName VARCHAR(50) NOT NULL,
    recordId INTEGER NOT NULL,
    fieldName longtext NOT NULL,
    oldValue longtext,
    newValue longtext,

	done_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 	done_by VARCHAR(50) DEFAULT "Admin" NOT NULL

) ENGINE = InnoDB DEFAULT CHARSET = UTF8;
