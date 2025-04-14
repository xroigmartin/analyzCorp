#!/bin/bash
# ./init-scripts/01-init-db.sh (Revisado: Lee APP_PASSWORD desde secret file)

set -e

# --- Rutas a los secretos ---
APP_PASSWORD_SECRET_FILE="/run/secrets/app_ow_password"
FLYWAY_PASSWORD_SECRET_FILE="/run/secrets/flyway_user_password"

# --- Comprobación de variables de entorno requeridas ---
# Ya no comprobamos APP_OW_PASSWORD aquí
if [[ -z "$POSTGRES_USER" || -z "$POSTGRES_DB" || -z "$APP_OW_USER" || -z "$APP_DB_NAME" ]]; then
  echo "ERROR: Variables requeridas (POSTGRES_USER, POSTGRES_DB, APP_OW_USER, APP_DB_NAME) deben definirse."
  exit 1
fi

# --- Leer la contraseña del archivo secreto ---
if [[ -f "$APP_PASSWORD_SECRET_FILE" && -r "$APP_PASSWORD_SECRET_FILE" ]]; then
    # Leer la contraseña del archivo, eliminar posible salto de línea final
    APP_PASSWORD_FROM_SECRET=$(cat "$APP_PASSWORD_SECRET_FILE" | tr -d '\n\r')
else
    echo "ERROR: Archivo secreto '$APP_PASSWORD_SECRET_FILE' no encontrado o sin permisos de lectura."
    exit 1
fi

# Verificar que la contraseña se leyó correctamente (no está vacía)
if [[ -z "$APP_PASSWORD_FROM_SECRET" ]]; then
    echo "ERROR: No se pudo leer la contraseña desde '$APP_PASSWORD_SECRET_FILE' o el archivo está vacío."
    exit 1
fi

if [[ -f "$FLYWAY_PASSWORD_SECRET_FILE" && -r "$FLYWAY_PASSWORD_SECRET_FILE" ]]; then
    FLYWAY_PASSWORD_FROM_SECRET=$(cat "$FLYWAY_PASSWORD_SECRET_FILE" | tr -d '\n\r')
else
    echo "ERROR: Archivo secreto '$FLYWAY_PASSWORD_SECRET_FILE' (Flyway) no encontrado o sin permisos." && exit 1
fi

if [[ -z "$FLYWAY_PASSWORD_FROM_SECRET" ]]; then
    echo "ERROR: No se pudo leer la contraseña (Flyway) desde '$FLYWAY_PASSWORD_SECRET_FILE' o está vacío." && exit 1
fi

echo "--- Iniciando script de inicialización de BD (01-init-db.sh) ---"

echo "--- Ejecutando Bloque 1: Creando Roles (App, Flyway), BD '$APP_DB_NAME', otorgando CONNECT y CREATE DB ---"
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL

    -- Crear Rol App si no existe
   DO \$\$ 
      BEGIN IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = '$APP_OW_USER') THEN 
         CREATE ROLE "$APP_OW_USER" WITH LOGIN PASSWORD '$APP_PASSWORD_FROM_SECRET'; 
      ELSE 
         RAISE NOTICE '[DEBUG] Rol App "%" ya existe.', '$APP_OW_USER'; 
      END IF; 
   END 
   \$\$;
    -- Crear Rol Flyway si no existe
   DO \$\$ 
      BEGIN 
         IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = '$FLYWAY_USER') THEN 
            CREATE ROLE "$FLYWAY_USER" WITH LOGIN PASSWORD '$FLYWAY_PASSWORD_FROM_SECRET'; 
         ELSE 
            RAISE NOTICE '[DEBUG] Rol Flyway "%" ya existe.', '$FLYWAY_USER'; 
         END IF;
      END 
   \$\$;

    -- Crear BD App si no existe
    SELECT 'CREATE DATABASE "' || '$APP_DB_NAME' || '"' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = '$APP_DB_NAME')\gexec

    -- Otorgar CONNECT en BD App a ambos roles
    GRANT CONNECT ON DATABASE "$APP_DB_NAME" TO "$APP_OW_USER";
    GRANT CONNECT ON DATABASE "$APP_DB_NAME" TO "$FLYWAY_USER";

    -- Otorgar CREATE en BD App al rol de Flyway (para que pueda crear esquemas)
    GRANT CREATE ON DATABASE "$APP_DB_NAME" TO "$FLYWAY_USER";

EOSQL
echo "--- Bloque 1 completado ---"


# --- Bloque 2: Conexión a la BD de la App ($APP_DB_NAME) como $POSTGRES_USER ---
# Crear Esquema Flyway y darle permisos
echo "--- Iniciando Bloque 2: Configurando esquema '$FLYWAY_SCHEMA' DENTRO de '$APP_DB_NAME' ---"
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$APP_DB_NAME" <<-EOSQL

    -- Crear esquema para Flyway si no existe
    CREATE SCHEMA IF NOT EXISTS "$FLYWAY_SCHEMA";

    -- Otorgar USAGE y CREATE sobre el esquema de Flyway al usuario de Flyway
    GRANT USAGE, CREATE ON SCHEMA "$FLYWAY_SCHEMA" TO "$FLYWAY_USER";
   
EOSQL
echo "--- Bloque 2 completado ---"

echo "--- Script de inicialización de BD (01-init-db.sh) completado exitosamente ---"