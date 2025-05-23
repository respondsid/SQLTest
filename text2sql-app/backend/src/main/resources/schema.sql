DROP TABLE IF EXISTS securities;
DROP TABLE IF EXISTS funds;
DROP TABLE IF EXISTS markets;

CREATE TABLE securities (
    symbol TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    type TEXT NOT NULL,
    sector TEXT,
    market_cap_category TEXT,
    is_open_for_investment BOOLEAN
);

CREATE TABLE funds (
    fund_name TEXT PRIMARY KEY,
    category TEXT NOT NULL,
    expense_ratio REAL,
    return_1y REAL,
    return_3y REAL
);

CREATE TABLE markets (
    market_code TEXT PRIMARY KEY,
    market_name TEXT NOT NULL
);
