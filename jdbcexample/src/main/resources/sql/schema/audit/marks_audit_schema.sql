CREATE DATABASE IF NOT EXISTS school ;
use school ;

CREATE TABLE IF NOT EXISTS audit_marks (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
    auditAction ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,

    recordId INTEGER NOT NULL,
    fieldName longtext NOT NULL,
    oldValue longtext,
    newValue longtext,

	done_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 	done_by VARCHAR(50) DEFAULT "Admin" NOT NULL

) ENGINE = InnoDB DEFAULT CHARSET = UTF8;