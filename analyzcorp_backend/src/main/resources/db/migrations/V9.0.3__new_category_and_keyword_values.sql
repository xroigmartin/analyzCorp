/*INSERT INTO personal_economy.category(name, created_at, created_by, updated_at, updated_by)
VALUES ('Nomina', now(), 'SYSTEM', now(), 'SYSTEM');

INSERT INTO personal_economy.category(name, created_at, created_by, updated_at, updated_by)
VALUES ('Extras', now(), 'SYSTEM', now(), 'SYSTEM');

INSERT INTO personal_economy.category(name, created_at, created_by, updated_at, updated_by)
VALUES ('Traspaso', now(), 'SYSTEM', now(), 'SYSTEM');

INSERT INTO personal_economy.category(name, created_at, created_by, updated_at, updated_by)
VALUES ('Ahorro', now(), 'SYSTEM', now(), 'SYSTEM');

INSERT INTO personal_economy.category(name, created_at, created_by, updated_at, updated_by)
VALUES ('Ropa', now(), 'SYSTEM', now(), 'SYSTEM');

INSERT INTO personal_economy.category(name, created_at, created_by, updated_at, updated_by)
VALUES ('Comercios', now(), 'SYSTEM', now(), 'SYSTEM');

INSERT INTO personal_economy.category(name, created_at, created_by, updated_at, updated_by)
VALUES ('Efectivo', now(), 'SYSTEM', now(), 'SYSTEM');

COMMIT;*/

INSERT INTO personal_economy.category_keyword (category_id, keyword, created_at, created_by, updated_at, updated_by)
VALUES
    ((SELECT id FROM personal_economy.category WHERE name = 'Hogar'), 'JARDILAND', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Coche'), 'Garancar', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Amazon'), 'AMZN', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Ropa'), 'Pampling', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Gas'), 'TOTALENERGIES', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Restaurantes'), 'WWW.ELCLUB', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Restaurantes'), 'BACOA', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Restaurantes'), 'MCD', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Restaurantes'), 'UDON', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Efectivo'), 'REINT.CAJERO', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Comercios'), 'EL CORTE INGLES', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Comercios'), 'ALE HOP', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Comercios'), 'PRIMAPRIX', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Comercios'), 'MERKAL', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Comida'), 'LIDL', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Comercios'), 'DRUNI', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Comunidad'), 'Recibo de fincas', now(), 'SYSTEM', now(), 'SYSTEM'),
    ((SELECT id FROM personal_economy.category WHERE name = 'Luz'), 'REPSOL S.L.U.REPS', now(), 'SYSTEM', now(), 'SYSTEM');
