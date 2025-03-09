-- 1. Eliminar la clave foránea actual
ALTER TABLE personal_economy.transaction
    DROP CONSTRAINT fk_transaction_category;

-- 2. Volver a crearla sin "ON DELETE SET DEFAULT"
ALTER TABLE personal_economy.transaction
    ADD CONSTRAINT fk_transaction_category
        FOREIGN KEY (category_id)
            REFERENCES personal_economy.category(id)
            ON DELETE RESTRICT;
