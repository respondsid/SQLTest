package com.example.text2sql.service;

public interface LlmService {
    String textToSql(String naturalLanguageQuery, String schema);
}
