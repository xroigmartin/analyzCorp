CREATE SCHEMA IF NOT EXISTS personal_economy;

GRANT USAGE ON SCHEMA personal_economy TO analyz_corp_ow;

ALTER DEFAULT PRIVILEGES FOR ROLE analyz_corp_flyway IN SCHEMA personal_economy
    GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO analyz_corp_ow;

ALTER DEFAULT PRIVILEGES FOR ROLE analyz_corp_flyway IN SCHEMA personal_economy
    GRANT USAGE, SELECT ON SEQUENCES TO analyz_corp_ow;

ALTER DEFAULT PRIVILEGES FOR ROLE analyz_corp_flyway IN SCHEMA personal_economy
    GRANT EXECUTE ON FUNCTIONS TO analyz_corp_ow;