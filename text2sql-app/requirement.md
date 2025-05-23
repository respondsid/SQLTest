# Business Requirements Document (BRD)

**Project Title:** Chat-Based Natural Language to SQL Interface – Financial Data PoC  
**Prepared For:** Internal PoC Stakeholders  
**Prepared By:** [Your Name]  
**Date:** 2025-05-21

---

## 1. Purpose and Business Motivation

This PoC is intended to validate the feasibility of allowing users to interact with structured financial datasets using natural language queries. It eliminates the need for SQL proficiency and enables quick, intuitive access to data insights via a conversational UI.

The system will use an LLM-based model to convert plain English queries into SQL, execute them against a local SQLite database, and display the result in a tabular format.

---

## 2. Scope

**In Scope:**
- Accepting user queries in plain English.
- Converting input queries into SQL using a text-to-SQL LLM model.
- Executing `SELECT` queries only on a SQLite database with sample financial data.
- Displaying results as a tabular grid.
- Returning the generated SQL query alongside the results.

**Out of Scope:**
- Data manipulation (INSERT/UPDATE/DELETE).
- Context retention or follow-up queries.
- Advanced visualizations or exports.

---

## 3. Target Users

- Business Analysts  
- Portfolio Managers  
- Internal Data Teams

---

## 4. Functional Requirements

| ID   | Requirement Description |
|------|--------------------------|
| FR1  | Angular frontend with a text input box for user queries |
| FR2  | Backend Spring Boot API to receive the query |
| FR3  | Integration with a text-to-SQL LLM to translate the input |
| FR4  | Execute the generated SQL against a local SQLite database |
| FR5  | Return query results and generated SQL to frontend |
| FR6  | Display result in a tabular grid format in the UI |

---

## 5. Sample Use Cases

- "Give me all securities which are in the tech sector and are open for new investors"
- "List ETFs with expense ratios less than 0.5%"
- "Show the top 10 large-cap mutual funds with the highest 3-year returns"
- "Which fixed-income funds had positive returns last quarter?"
- "Show all stocks listed in the US market with P/E ratio under 15"

---

## 6. Data Requirements

A seeded SQLite database will include dummy data modeled on Morningstar-style security metadata.

### Key Tables

- `securities`:
  - `symbol` (string)
  - `name` (string)
  - `type` (e.g., stock, ETF, mutual fund)
  - `sector` (e.g., tech, healthcare)
  - `market_cap_category` (e.g., large, mid, small)
  - `is_open_for_investment` (boolean)

- `funds`:
  - `fund_name` (string)
  - `category` (e.g., equity, fixed income)
  - `expense_ratio` (float)
  - `return_1y` (float)
  - `return_3y` (float)

- `markets`:
  - `market_code` (string)
  - `market_name` (string)

---

## 7. Non-Functional Requirements

| Category     | Requirement                             |
|--------------|------------------------------------------|
| Performance  | Query response within 2–3 seconds         |
| Security     | No authentication for MVP, runs locally   |
| Reliability  | Handle invalid SQL or empty result gracefully |
| Extensibility| Design to support other databases or models in future |

---

## 8. Architecture Overview

- **Frontend:** Angular-based UI with a text input and results table  
- **Backend:** Spring Boot REST API  
- **LLM Model:** SQLCoder  
- **Database:** SQLite with seeded financial data

---

## 9. MVP Acceptance Criteria

- Frontend accepts natural language query and displays results in a table.
- Backend converts query to SQL and successfully executes against dummy data.
- SQL is shown alongside result for debugging.
- Handles invalid queries without crash.

