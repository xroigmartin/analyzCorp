CREATE TABLE analyz_corp.company_ticker (
    cik int8 GENERATED ALWAYS AS IDENTITY NOT NULL,
    ticker text NOT NULL,
    title text NOT NULL,
    CONSTRAINT company_ticker_pk PRIMARY KEY (cik),
    CONSTRAINT company_ticker_unique UNIQUE (ticker)
);
CREATE INDEX company_ticker_title_idx ON analyz_corp.company_ticker (title);
CREATE UNIQUE INDEX company_ticker_ticker_idx ON analyz_corp.company_ticker (ticker);