
-- Setup DB

source /docker-entrypoint-initdb.d/db_settings/db_settings.sql;

-- Load schemas

source /docker-entrypoint-initdb.d/schema/classes_schema.sql;
source /docker-entrypoint-initdb.d/schema/classes_statistics_schema.sql;
source /docker-entrypoint-initdb.d/schema/marks_schema.sql;
source /docker-entrypoint-initdb.d/schema/pupils_schema.sql;
source /docker-entrypoint-initdb.d/schema/parents_schema.sql;
source /docker-entrypoint-initdb.d/schema/subjects_schema.sql;
source /docker-entrypoint-initdb.d/schema/teachers_schema.sql;

-- Load audit schemas

source /docker-entrypoint-initdb.d/schema/audit/classes_audit_schema.sql;
source /docker-entrypoint-initdb.d/schema/audit/classes_statistics_audit_schema.sql;
source /docker-entrypoint-initdb.d/schema/audit/marks_audit_schema.sql;
source /docker-entrypoint-initdb.d/schema/audit/pupils_audit_schema.sql;
source /docker-entrypoint-initdb.d/schema/audit/parents_audit_schema.sql;
source /docker-entrypoint-initdb.d/schema/audit/subjects_audit_schema.sql;
source /docker-entrypoint-initdb.d/schema/audit/teachers_audit_schema.sql;

source /docker-entrypoint-initdb.d/schema/audit/generic_audit_schema.sql;

-- Load data

source /docker-entrypoint-initdb.d/data/classes_data.sql;
source /docker-entrypoint-initdb.d/data/classes_statistics_data.sql;
source /docker-entrypoint-initdb.d/data/marks_data.sql;
source /docker-entrypoint-initdb.d/data/pupils_data.sql;
source /docker-entrypoint-initdb.d/data/parents_data.sql;
source /docker-entrypoint-initdb.d/data/subjects_data.sql;
source /docker-entrypoint-initdb.d/data/teachers_data.sql;

-- Load data alterations



-- Load functions

source /docker-entrypoint-initdb.d/functions/random_data_generation_functions.sql;

-- Load stored procedures

source /docker-entrypoint-initdb.d/storedProc/GenNewClass_storedProc.sql;
source /docker-entrypoint-initdb.d/storedProc/AuditRevision_storedProc.sql;
source /docker-entrypoint-initdb.d/storedProc/Statistics_email_storedProc.sql;
source /docker-entrypoint-initdb.d/storedProc/Statistics_pupils_relatives.sql;

-- Load triggers

source /docker-entrypoint-initdb.d/triggers/triggers.sql;

-- Load audit specific triggers

source /docker-entrypoint-initdb.d/triggers/audit_triggers/classes_audit_triggers.sql;
source /docker-entrypoint-initdb.d/triggers/audit_triggers/marks_audit_triggers.sql;
source /docker-entrypoint-initdb.d/triggers/audit_triggers/classes_statistics_audit_triggers.sql
source /docker-entrypoint-initdb.d/triggers/audit_triggers/pupils_audit_triggers.sql;
source /docker-entrypoint-initdb.d/triggers/audit_triggers/parents_audit_triggers.sql;
source /docker-entrypoint-initdb.d/triggers/audit_triggers/subjects_audit_triggers.sql;
source /docker-entrypoint-initdb.d/triggers/audit_triggers/teachers_audit_triggers.sql;

-- Load validation specific triggers

source /docker-entrypoint-initdb.d/triggers/validation_triggers/classes_validation_triggers.sql;
source /docker-entrypoint-initdb.d/triggers/validation_triggers/marks_validation_triggers.sql;
source /docker-entrypoint-initdb.d/triggers/validation_triggers/classes_statistics_validation_triggers.sql;
source /docker-entrypoint-initdb.d/triggers/validation_triggers/pupils_validation_triggers.sql;
source /docker-entrypoint-initdb.d/triggers/validation_triggers/parents_validation_triggers.sql;
source /docker-entrypoint-initdb.d/triggers/validation_triggers/subjects_validation_triggers.sql;
source /docker-entrypoint-initdb.d/triggers/validation_triggers/teachers_validation_triggers.sql;

-- Load views

source /docker-entrypoint-initdb.d/views/AllPersons_view.sql
source /docker-entrypoint-initdb.d/views/FamilyList_view.sql

-- Load events
