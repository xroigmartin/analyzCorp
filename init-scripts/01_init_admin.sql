CREATE USER analyz_corp_adm WITH PASSWORD '${ANALYZ_CORP_ADM_PASS}';

DO $$
    DECLARE
        schema_name_privileges text;
    BEGIN
        FOR schema_name_privileges IN
            SELECT schema_name
            FROM information_schema.schemata
            WHERE schema_name NOT IN ('pg_catalog', 'information_schema')
            LOOP
                EXECUTE format('GRANT ALL PRIVILEGES ON SCHEMA %I TO analyz_corp_adm', schema_name_privileges);
                EXECUTE format('GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA %I TO analyz_corp_adm', schema_name_privileges);
                EXECUTE format('GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA %I TO analyz_corp_adm', schema_name_privileges);
            END LOOP;
    END
$$;

ALTER DEFAULT PRIVILEGES FOR ROLE analyz_corp_adm
    IN SCHEMA PUBLIC
    GRANT ALL PRIVILEGES ON TABLES TO analyz_corp_adm;

ALTER DEFAULT PRIVILEGES FOR ROLE analyz_corp_adm
    IN SCHEMA PUBLIC
    GRANT ALL PRIVILEGES ON SEQUENCES TO analyz_corp_adm;