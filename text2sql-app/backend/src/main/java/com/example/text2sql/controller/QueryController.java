package com.example.text2sql.controller;

import com.example.text2sql.service.LlmService;
import com.example.text2sql.service.SqlExecutionService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200") // Allow requests from Angular frontend
public class QueryController {

    private final LlmService llmService;
    private final SqlExecutionService sqlExecutionService;
    private final ResourceLoader resourceLoader;

    public QueryController(LlmService llmService, SqlExecutionService sqlExecutionService, ResourceLoader resourceLoader) {
        this.llmService = llmService;
        this.sqlExecutionService = sqlExecutionService;
        this.resourceLoader = resourceLoader;
    }

    @PostMapping("/query")
    public ResponseEntity<Map<String, Object>> executeQuery(@RequestBody Map<String, String> request) {
        String naturalLanguageQuery = request.get("query");
        String schema = getSchemaInformation();

        Map<String, Object> response = new HashMap<>();
        String generatedSql = null;
        List<Map<String, Object>> queryResults;

        try {
            generatedSql = llmService.textToSql(naturalLanguageQuery, schema);
            response.put("generatedSql", generatedSql);

            queryResults = sqlExecutionService.executeSql(generatedSql);
            response.put("queryResults", queryResults);

            return ResponseEntity.ok(response);
        } catch (SQLException e) {
            response.put("error", "SQL Execution Error: " + e.getMessage());
            response.put("generatedSql", generatedSql);
            return ResponseEntity.status(500).body(response);
        } catch (Exception e) {
            response.put("error", "An unexpected error occurred: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    private String getSchemaInformation() {
        try {
            Resource resource = resourceLoader.getResource("classpath:schema.sql");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                return reader.lines().collect(Collectors.joining("\n"));
            }
        } catch (IOException e) {
            System.err.println("Error reading schema.sql: " + e.getMessage());
            return "Error: Could not load schema information.";
        }
    }
}
