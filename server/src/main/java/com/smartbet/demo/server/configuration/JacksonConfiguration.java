package com.smartbet.demo.server.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartbet.demo.objectmapping.ObjectMapperProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JacksonConfiguration {
    @Bean
    @Primary
    public ObjectMapper objectMapper(ObjectMapperProvider objectMapperProvider) {
        return objectMapperProvider.buildObjectMapper();
    }
}
