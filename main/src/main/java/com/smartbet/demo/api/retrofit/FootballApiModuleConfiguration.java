package com.smartbet.demo.api.retrofit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FootballApiModuleConfiguration {
    @Bean
    public FootballApiConfiguration footballApiConfiguration(MutableFootballApiConfiguration mutableFootballApiConfiguration) {
        return mutableFootballApiConfiguration.toFootballApiConfiguration();
    }
}
