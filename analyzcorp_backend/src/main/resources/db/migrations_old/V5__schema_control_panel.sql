CREATE SCHEMA control_panel AUTHORIZATION analyz_corp_container_user;

CREATE TABLE control_panel.currencies
AS SELECT * from personal_economy.currency;

ALTER TABLE control_panel.currencies ADD CONSTRAINT currencies_pk PRIMARY KEY (code);

ALTER TABLE personal_economy."transaction" DROP CONSTRAINT fk_currency;
ALTER TABLE personal_economy."transaction" ADD CONSTRAINT transaction_currency_fk FOREIGN KEY (currency) REFERENCES control_panel.currencies(code);

DROP TABLE personal_economy.currency;

