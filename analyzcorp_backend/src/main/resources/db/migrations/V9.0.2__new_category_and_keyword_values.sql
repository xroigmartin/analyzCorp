INSERT INTO personal_economy.category(name, created_at, created_by, updated_at, updated_by)
VALUES ('Suscripciones', now(), 'SYSTEM', now(), 'SYSTEM');

COMMIT;

INSERT INTO personal_economy.category_keyword (category_id, keyword, created_at, created_by, updated_at, updated_by)
VALUES
    ((SELECT id FROM personal_economy.category WHERE name = 'Hipoteca'), 'PRES', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Seguros'), 'RECIBO UNICO MYBOX', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Comida'), 'CONSUM', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Suscripciones'), 'NETFLIX', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Gimnasio'), 'QSPORT ', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Osmosis'), 'HIDROSALUD', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Gasolina'), 'WAYLET', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Telefono'), 'DIGI ', now(), 'SYSTEM', now(), 'SYSTEM');


