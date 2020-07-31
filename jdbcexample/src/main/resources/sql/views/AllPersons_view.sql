CREATE DATABASE IF NOT EXISTS school ;
use school ;

-- Unfortunatelly You cannot create autoincrement generation in views -> use stored procedures or temporary tables

DROP VIEW IF EXISTS `persons`;
CREATE VIEW `persons`(`firstname`, `lastname`, `email`, `tbl_name`) AS
    SELECT `firstname`,`lastname`,`email`, "pupils" as `tbl_name` FROM `pupils`
        UNION
    SELECT `firstname`,`lastname`,`email`, "teachers"  as `tbl_name` FROM `teachers`
        UNION
    SELECT `firstname`,`lastname`,`email`, "parents"  as `tbl_name` FROM `parents`;
