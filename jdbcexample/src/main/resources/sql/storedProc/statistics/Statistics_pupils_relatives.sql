CREATE DATABASE IF NOT EXISTS school ;
use school ;

DELIMITER $$

DROP PROCEDURE IF EXISTS GET_PUPILS_RELATIVES;
CREATE PROCEDURE GET_PUPILS_RELATIVES()
NOT DETERMINISTIC
BEGIN

    SET @@sql_mode = "";

    SELECT tbl1.familyId AS familyId,
     tbl1.id AS id1,
     tbl1.firstname AS firstname1,
     tbl1.lastname AS lastname1,
     tbl1.email AS email1,
     tbl2.id AS id2,
     tbl2.firstname AS firstname2,
     tbl2.lastname AS lastname2,
     tbl2.email AS email2
    FROM pupils tbl1
    INNER JOIN pupils tbl2
    ON tbl1.familyId=tbl2.familyId
    WHERE tbl1.id <> tbl2.id
    GROUP BY tbl1.familyId ;

    SET @@sql_mode = "ONLY_FULL_GROUP_BY";
END$$

DELIMITER ;

-- call GET_PUPILS_RELATIVES();