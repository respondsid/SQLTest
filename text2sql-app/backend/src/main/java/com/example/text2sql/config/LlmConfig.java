package com.example.text2sql.config;

import com.example.text2sql.service.LlmService;
import com.example.text2sql.service.MockLlmService;
import com.example.text2sql.service.OpenAILlmService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LlmConfig {

    @Value("${llm.service.type:mock}") // Default to mock if not specified
    private String llmServiceType;

    private final MockLlmService mockLlmService;
    private final OpenAILlmService openAILlmService;

    public LlmConfig(MockLlmService mockLlmService, OpenAILlmService openAILlmService) {
        this.mockLlmService = mockLlmService;
        this.openAILlmService = openAILlmService;
    }

    @Bean
    public LlmService llmService() {
        if ("openai".equalsIgnoreCase(llmServiceType)) {
            return openAILlmService;
        }
        // Default or fallback to mock
        return mockLlmService;
    }
}
