INSERT INTO personal_economy.category_keyword (category_id, keyword, created_at, created_by, updated_at, updated_by)
VALUES
    ((SELECT id FROM personal_economy.category WHERE name = 'Traspaso'), 'Traspaso', now(), 'SYSTEM', now(), 'SYSTEM');
