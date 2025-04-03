DROP TABLE personal_economy.budget_category;
DROP TABLE personal_economy.budget;

CREATE TABLE personal_economy.budget (
    id SERIAL PRIMARY KEY,
    category_id INTEGER NOT NULL,
    amount NUMERIC(15, 2) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    created_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_by VARCHAR(50) NOT NULL,
    CONSTRAINT fk_budget_category FOREIGN KEY (category_id)
     REFERENCES personal_economy.category (id) ON DELETE CASCADE,
    CONSTRAINT check_start_end_dates CHECK (start_date <= end_date)
);

CREATE INDEX idx_budget_category_id ON personal_economy.budget (category_id);
CREATE INDEX idx_budget_start_date ON personal_economy.budget (start_date);
CREATE INDEX idx_budget_end_date ON personal_economy.budget (end_date);
CREATE INDEX idx_budget_start_end_date ON personal_economy.budget (start_date, end_date);