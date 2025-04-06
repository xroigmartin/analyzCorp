ALTER TABLE personal_economy.budget
ADD COLUMN account_id INTEGER;

ALTER TABLE personal_economy.budget
ADD CONSTRAINT fk_budget_account
        FOREIGN KEY (account_id)
            REFERENCES personal_economy.account(id)
            ON DELETE RESTRICT;