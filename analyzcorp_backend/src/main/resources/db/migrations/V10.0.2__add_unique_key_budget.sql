ALTER TABLE personal_economy.budget
ADD CONSTRAINT unique_budget_category_start_end
UNIQUE (category_id, start_date, end_date);