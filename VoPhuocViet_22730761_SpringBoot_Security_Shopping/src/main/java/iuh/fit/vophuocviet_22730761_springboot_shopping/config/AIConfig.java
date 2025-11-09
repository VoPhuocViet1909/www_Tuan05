package iuh.fit.vophuocviet_22730761_springboot_shopping.config;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.openai.chat.base-url}")
    private String baseUrl;

    @Value("${spring.ai.openai.chat.options.model}")
    private String model;

    @Bean
    public ChatModel chatModel() {
        // Tạo OpenAiApi với custom base URL (Gemini endpoint)
        OpenAiApi openAiApi = new OpenAiApi(baseUrl, apiKey);

        // Tạo ChatModel với options
        return new OpenAiChatModel(openAiApi,
                OpenAiChatOptions.builder()
                        .withModel(model)
                        .withTemperature(0.7)
                        .withMaxTokens(1000)
                        .build()
        );
    }
}