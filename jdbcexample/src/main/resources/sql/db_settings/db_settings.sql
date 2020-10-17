-- This file will be executed on db first

SET @@sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY,',''));

DROP DATABASE IF EXISTS school ;
CREATE DATABASE IF NOT EXISTS school ;
use school ;

-- grant file ON *.*  to 'admin'@'%';
-- flush privileges;