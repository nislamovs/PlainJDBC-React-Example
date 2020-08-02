CREATE DATABASE IF NOT EXISTS school ;
use school ;

-- Unfortunatelly You cannot create autoincrement generation in views -> use stored procedures or temporary tables

DROP VIEW IF EXISTS `families`;
CREATE VIEW `families`(`firstname`, `lastname`, `email`, `whoIs`, `memberType`, `familyId`) AS
    SELECT * FROM (
        SELECT firstname, lastname, email, familyId, "parent" AS whoIs, parentType AS memberType FROM parents
        UNION
        SELECT firstname, lastname, email, familyId, "pupil" AS whoIs,
            CASE
                WHEN pupils.gender = 'FEMALE' THEN 'GIRL' ELSE 'BOY'
            END AS memberType
        FROM pupils
    ) AS tbl
    ORDER BY tbl.familyId ASC

-- select * from pupils
-- join parents where pupils.familyId=parents.familyId

