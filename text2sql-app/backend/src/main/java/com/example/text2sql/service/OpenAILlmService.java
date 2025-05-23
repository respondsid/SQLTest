package com.example.text2sql.service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;

@Service
public class OpenAILlmService implements LlmService {

    private final OpenAiService openAiService;
    private final String model;

    public OpenAILlmService(@Value("${openai.api.key}") String apiKey,
                            @Value("${openai.model:gpt-3.5-turbo}") String model) {
        this.openAiService = new OpenAiService(apiKey, Duration.ofSeconds(30));
        this.model = model;
    }

    @Override
    public String textToSql(String naturalLanguageQuery, String schema) {
        String prompt = "Given the following database schema:\n" +
                        schema +
                        "\n\nConvert the following natural language query into a SQL SELECT statement for SQLite:\n" +
                        naturalLanguageQuery +
                        "\n\nSQL:";

        ChatMessage userMessage = new ChatMessage("user", prompt);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model(model)
                .messages(Collections.singletonList(userMessage))
                .maxTokens(200) // Limit response length for SQL
                .build();

        try {
            return openAiService.createChatCompletion(chatCompletionRequest)
                    .getChoices()
                    .get(0)
                    .getMessage()
                    .getContent()
                    .trim();
        } catch (Exception e) {
            // Log the error and return a default or error message
            System.err.println("Error calling OpenAI API: " + e.getMessage());
            return "SELECT 'Error: Could not generate SQL from OpenAI. " + e.getMessage() + "' AS error_message;";
        }
    }
}
