CREATE TABLE personal_economy.bank_account (
  id serial4 NOT NULL,
  bank_name varchar NOT NULL,
  iban varchar NOT NULL,
  alias varchar NULL,
  CONSTRAINT bank_account_pk PRIMARY KEY (id),
  CONSTRAINT bank_account_unique UNIQUE (iban)
);
CREATE UNIQUE INDEX bank_account_iban_idx ON personal_economy.bank_account USING btree (iban);

CREATE TABLE personal_economy.bank_account_transaction_category (
   id serial4 NOT NULL,
   name varchar NOT NULL,
   type varchar NOT NULL,
   CONSTRAINT bank_account_transaction_category_pk PRIMARY KEY (id),
   CONSTRAINT bank_account_transaction_category_unique UNIQUE (name)
);

-- personal_economy.bank_account_transaction definition
CREATE TABLE personal_economy.bank_account_transaction (
    id bigserial NOT NULL,
    bank_account_transaction_category_id int4 NULL,
    quantity numeric NOT NULL,
    transaction_date date NOT NULL,
    transaction_type varchar NOT NULL,
    description varchar NOT NULL,
    CONSTRAINT bank_account_transaction_pk PRIMARY KEY (id),
    CONSTRAINT bank_account_transaction_category_fk FOREIGN KEY (bank_account_transaction_category_id) REFERENCES personal_economy.bank_account_transaction_category(id)
);
CREATE INDEX bank_account_transaction_category_idx ON personal_economy.bank_account_transaction USING btree (bank_account_transaction_category_id);
CREATE INDEX bank_account_transaction_type_idx ON personal_economy.bank_account_transaction USING btree (transaction_type);

-- personal_economy.budget definition
CREATE TABLE personal_economy.budget (
    id serial4 NOT NULL,
    bank_account_id int4 NOT NULL,
    bank_account_category_id int4 NOT NULL,
    quantity numeric NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    CONSTRAINT budget_pk PRIMARY KEY (id),
    CONSTRAINT budget_unique UNIQUE (bank_account_id, bank_account_category_id, start_date, end_date),
    CONSTRAINT budget_bank_account_fk FOREIGN KEY (bank_account_id) REFERENCES personal_economy.bank_account(id),
    CONSTRAINT budget_transaction_category_fk FOREIGN KEY (bank_account_category_id) REFERENCES personal_economy.bank_account_transaction_category(id)
);
CREATE INDEX budget_account_idx ON personal_economy.budget USING btree (bank_account_id, bank_account_category_id, start_date DESC, end_date DESC);