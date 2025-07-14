package com.chatroom.config; // Or your equivalent config package

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    // This annotation MUST match the key in your application.properties
    @Value("${services.message-service.url}")
    private String messageServiceUrl;

    /**
     * Creates a WebClient bean configured to communicate with the message-service.
     * It's good practice to create a specific bean for each service you call.
     *
     * @return A configured WebClient instance.
     */
    @Bean("messageServiceWebClient") // Naming the bean is a good practice
    public WebClient messageServiceWebClient() {
        return WebClient.builder()
                .baseUrl(messageServiceUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}