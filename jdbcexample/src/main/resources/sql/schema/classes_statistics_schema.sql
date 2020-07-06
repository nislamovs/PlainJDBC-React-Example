CREATE DATABASE IF NOT EXISTS school ;
use school ;

CREATE TABLE IF NOT EXISTS classes_statistics (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	class_id INTEGER,
	class_name VARCHAR(50),
	created DATE,
	pupil_count INTEGER,


	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 	created_by varchar(50) DEFAULT "Admin",
	modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 	modified_by varchar(50) DEFAULT "Admin"

) ENGINE = InnoDB DEFAULT CHARSET=utf8;

