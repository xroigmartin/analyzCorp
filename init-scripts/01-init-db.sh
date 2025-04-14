#!/bin/bash
# ./init-scripts/01-init-db.sh (Revisado: Lee APP_PASSWORD desde secret file)

set -e

# --- Rutas a los secretos ---
APP_PASSWORD_SECRET_FILE="/run/secrets/app_ow_password"

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

echo "--- Iniciando script de inicialización de BD (01-init-db.sh) ---"

echo "--- Ejecutando: Creando Rol y BD '$APP_DB_NAME', otorgando CONNECT ---"
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL

    -- Crear Rol/Usuario para la aplicación si no existe
    DO \$\$
    BEGIN
       RAISE NOTICE '[DEBUG] Creando rol "%" ...', '$APP_OW_USER';
       IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = '$APP_OW_USER') THEN
          -- Usar la contraseña leída desde el archivo secreto
          CREATE ROLE "$APP_OW_USER" WITH LOGIN PASSWORD '$APP_PASSWORD_FROM_SECRET';
       END IF;
    END
    \$\$;

    -- Crear la Base de Datos para la aplicación si no existe
    SELECT 'CREATE DATABASE "' || '$APP_DB_NAME' || '"' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = '$APP_DB_NAME')\gexec

    -- Otorgar permiso para conectar a la NUEVA base de datos de la app
    GRANT CONNECT ON DATABASE "$APP_DB_NAME" TO "$APP_OW_USER";

EOSQL

echo "--- Script de inicialización de BD (01-init-db.sh) completado exitosamente ---"