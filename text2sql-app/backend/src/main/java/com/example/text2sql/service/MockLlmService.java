package com.example.text2sql.service;

import org.springframework.stereotype.Service;

@Service
public class MockLlmService implements LlmService {

    @Override
    public String textToSql(String naturalLanguageQuery, String schema) {
        // This is a mock implementation. In a real scenario, this would call an LLM API.
        // For demonstration, we'll return a simple SELECT query.
        // You can extend this to return different queries based on input for testing.
        if (naturalLanguageQuery.toLowerCase().contains("securities")) {
            return "SELECT * FROM securities LIMIT 5;";
        } else if (naturalLanguageQuery.toLowerCase().contains("funds")) {
            return "SELECT * FROM funds LIMIT 5;";
        } else if (naturalLanguageQuery.toLowerCase().contains("markets")) {
            return "SELECT * FROM markets LIMIT 5;";
        } else {
            return "SELECT 'Please ask about securities, funds, or markets.' AS message;";
        }
    }
}
