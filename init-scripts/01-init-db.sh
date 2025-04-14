#!/bin/bash
set -e

if [[ -z "$POSTGRES_USER" || -z "$POSTGRES_DB" || -z "$APP_OW_USER" || -z "$APP_OW_PASSWORD" || -z "$APP_DB_NAME" ]]; then
  echo "ERROR: Variables requeridas (POSTGRES_USER, POSTGRES_DB, APP_OW_USER, APP_OW_PASSWORD, APP_DB_NAME) deben definirse."
  exit 1
fi

echo "--- Execute: Create rol '$APP_OW_USER' and DB '$APP_DB_NAME', assign CONNECT ---"
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL

    DO \$\$
    BEGIN
       IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = '$APP_OW_USER') THEN
          CREATE ROLE "$APP_OW_USER" WITH LOGIN PASSWORD '$APP_OW_PASSWORD';
       ELSE
          RAISE NOTICE '[DEBUG] Exists Rol "%"', '$APP_OW_USER';
       END IF;
    END
    \$\$;

    SELECT 'CREATE DATABASE "' || '$APP_DB_NAME' || '"' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = '$APP_DB_NAME')\gexec
  
    GRANT CONNECT ON DATABASE "$APP_DB_NAME" TO "$APP_OW_USER";

EOSQL

echo "--- Inicialization script (01-init-db.sh) completed ---"