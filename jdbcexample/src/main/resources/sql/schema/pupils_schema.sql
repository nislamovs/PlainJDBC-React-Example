CREATE DATABASE IF NOT EXISTS school ;
use school ;

CREATE TABLE IF NOT EXISTS pupils (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	firstname VARCHAR(50),
	lastname VARCHAR(50),
	email VARCHAR(50),
	gender ENUM('MALE', 'FEMALE') NOT NULL,
	birthdate DATE,
	class_id INTEGER,
	class_head_id INTEGER,


	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 	created_by varchar(50) DEFAULT "Admin",
	modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 	modified_by varchar(50) DEFAULT "Admin",


 	CONSTRAINT CHK_Email  CHECK (REGEXP_LIKE(email, '^[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$'))

) ENGINE = InnoDB DEFAULT CHARSET=utf8;