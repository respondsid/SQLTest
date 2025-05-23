INSERT INTO securities (symbol, name, type, sector, market_cap_category, is_open_for_investment) VALUES
('AAPL', 'Apple Inc.', 'stock', 'tech', 'large', TRUE),
('MSFT', 'Microsoft Corp.', 'stock', 'tech', 'large', TRUE),
('GOOGL', 'Alphabet Inc. (Class A)', 'stock', 'tech', 'large', FALSE),
('JPM', 'JPMorgan Chase & Co.', 'stock', 'financials', 'large', TRUE),
('VFINX', 'Vanguard 500 Index Fund Investor Shares', 'mutual fund', 'diversified', 'large', TRUE),
('SPY', 'SPDR S&P 500 ETF Trust', 'ETF', 'diversified', 'large', TRUE),
('QQQ', 'Invesco QQQ Trust', 'ETF', 'tech', 'large', TRUE);

INSERT INTO funds (fund_name, category, expense_ratio, return_1y, return_3y) VALUES
('Vanguard 500 Index Fund Investor Shares', 'equity', 0.14, 15.2, 12.8),
('Fidelity Contrafund', 'equity', 0.80, 18.5, 14.1),
('PIMCO Income Fund', 'fixed income', 0.95, 5.1, 4.5),
('iShares Core U.S. Aggregate Bond ETF', 'fixed income', 0.05, 2.3, 1.8),
('ARK Innovation ETF', 'equity', 0.75, -10.5, 8.2);

INSERT INTO markets (market_code, market_name) VALUES
('US', 'United States'),
('NASDAQ', 'NASDAQ Stock Market'),
('NYSE', 'New York Stock Exchange');
