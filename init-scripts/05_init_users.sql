CREATE ROLE analyz_corp_readers;
CREATE ROLE analyz_corp_writers;

GRANT USAGE ON SCHEMA analyz_corp TO analyz_corp_writers;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA analyz_corp TO analyz_corp_writers;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA analyz_corp TO analyz_corp_writers;
ALTER DEFAULT PRIVILEGES IN SCHEMA analyz_corp
  GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO analyz_corp_writers;

GRANT USAGE ON SCHEMA analyz_corp TO analyz_corp_readers;
GRANT SELECT ON ALL TABLES IN SCHEMA analyz_corp TO analyz_corp_readers;
ALTER DEFAULT PRIVILEGES IN SCHEMA analyz_corp
  GRANT SELECT ON TABLES TO analyz_corp_readers;

CREATE USER analyz_corp_read WITH PASSWORD '${ANALYZ_CORP_READ_USER_PASS}';
GRANT analyz_corp_readers TO analyz_corp_read;

CREATE USER analyz_corp_ow WITH PASSWORD '${ANALYZ_CORP_OW_USER_PASS}';
GRANT analyz_corp_writers TO analyz_corp_ow;