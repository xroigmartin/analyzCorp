CREATE TABLE personal_economy.account (
    id SERIAL PRIMARY KEY,
    bank_name varchar(255) NOT NULL,
    iban varchar(34) NOT NULL UNIQUE,
    alias varchar(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    created_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_by VARCHAR(50) NOT NULL
);

CREATE TABLE personal_economy.currency (
   code VARCHAR(3) PRIMARY KEY,
   name VARCHAR(50) NOT NULL
);

CREATE TABLE personal_economy.transaction (
    id SERIAL PRIMARY KEY,
    amount NUMERIC(15, 2) NOT NULL,
    currency VARCHAR(3) NOT NULL DEFAULT 'EUR',
    date TIMESTAMP WITH TIME ZONE NOT NULL,
    type VARCHAR(50) NOT NULL,
    description TEXT,
    account_id INTEGER NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    created_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_by VARCHAR(50) NOT NULL,
    CONSTRAINT fk_account FOREIGN KEY (account_id)
      REFERENCES personal_economy.account (id) ON DELETE CASCADE,
    CONSTRAINT fk_currency FOREIGN KEY (currency)
        REFERENCES personal_economy.currency (code)
);

CREATE TABLE personal_economy.category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    created_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_by VARCHAR(50) NOT NULL
);

CREATE TABLE personal_economy.transaction_category (
    transaction_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    created_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_by VARCHAR(50) NOT NULL,
    PRIMARY KEY (transaction_id, category_id),
    CONSTRAINT fk_transaction FOREIGN KEY (transaction_id)
        REFERENCES personal_economy.transaction (id) ON DELETE CASCADE,
    CONSTRAINT fk_category FOREIGN KEY (category_id)
        REFERENCES personal_economy.category (id) ON DELETE CASCADE
);

-- personal_economy.budget definition
CREATE TABLE personal_economy.budget (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(255) NOT NULL,
    amount NUMERIC(15,2) NOT NULL,
    start_date TIMESTAMP WITH TIME ZONE NOT NULL,
    end_date TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    created_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_by VARCHAR(50) NOT NULL,
    CONSTRAINT chk_budget_date_range CHECK (start_date < end_date)
);

CREATE TABLE personal_economy.budget_category (
    budget_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    created_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_by VARCHAR(50) NOT NULL,
    PRIMARY KEY (budget_id, category_id),
    CONSTRAINT fk_budget FOREIGN KEY (budget_id)
        REFERENCES personal_economy.budget (id) ON DELETE CASCADE,
    CONSTRAINT fk_budget_category FOREIGN KEY (category_id)
        REFERENCES personal_economy.category (id) ON DELETE CASCADE
);

CREATE INDEX idx_transaction_account ON personal_economy.transaction(account_id);
CREATE INDEX idx_transaction_category_transaction ON personal_economy.transaction_category(transaction_id);
CREATE INDEX idx_transaction_category_category ON personal_economy.transaction_category(category_id);
CREATE INDEX idx_budget_category_budget ON personal_economy.budget_category(budget_id);
CREATE INDEX idx_budget_category_category ON personal_economy.budget_category(category_id);
CREATE UNIQUE INDEX idx_transaction_category_unique
    ON personal_economy.transaction_category (transaction_id, category_id);
