CREATE DATABASE IF NOT EXISTS school ;
use school ;

DELIMITER $$

DROP PROCEDURE IF EXISTS GET_EMAIL_PROVIDER_STATS;
CREATE PROCEDURE GET_EMAIL_PROVIDER_STATS(IN TBL_NAME VARCHAR(30))
BEGIN
    START TRANSACTION;
        SET @TBL_NAME = (
            SELECT CASE
                WHEN TBL_NAME = 'pupils'   THEN 'pupils'
                WHEN TBL_NAME = 'teachers' THEN 'teachers'
                WHEN TBL_NAME = 'parents'  THEN 'parents'
                WHEN TBL_NAME = 'persons'  THEN 'persons'
                ELSE 'persons'
            END
        );

        CREATE TEMPORARY TABLE TempTable
        (
           id INTEGER AUTO_INCREMENT NOT NULL,
           domain VARCHAR(20),
           count VARCHAR(20),
           primary key (id)
        ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

        SET @sql_part1 = "
            INSERT INTO
                TempTable(domain, count)
            SELECT
            CASE
                WHEN RIGHT(email, length(email) - POSITION('@' in email)) = 'gmail.com' THEN 'gmail.com'
                WHEN RIGHT(email, length(email) - POSITION('@' in email)) = 'inbox.lv' THEN 'inbox.lv'
                WHEN RIGHT(email, length(email) - POSITION('@' in email)) = 'mail.ru' THEN 'mail.ru'
                WHEN RIGHT(email, length(email) - POSITION('@' in email)) = 'yahoo.com' THEN 'yahoo.com'
                ELSE 'other'
            END AS domain,
            COUNT(*) as count
            FROM ";

        SET @sql_part2 = "
            WHERE  length(email) > 0
            GROUP BY domain
            ORDER BY count ASC ;
        ";

        SET @table_stats_query = CONCAT(@sql_part1, @TBL_NAME, @sql_part2);

        PREPARE stmt FROM @table_stats_query;
        EXECUTE stmt;
        DEALLOCATE PREPARE stmt;

        SELECT * FROM TempTable;
        DROP TABLE TempTable;

    COMMIT;
END$$

DELIMITER ;

-- call GET_EMAIL_PROVIDER_STATS('pupils');
-- call GET_EMAIL_PROVIDER_STATS('persons');