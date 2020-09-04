package com.smartbet.demo.api.retrofit;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("api.footballapi")
@Component
@Data
public class MutableFootballApiConfiguration {
    private String host;
    private String key;

    FootballApiConfiguration toFootballApiConfiguration() {
        return new FootballApiConfiguration(host, key);
    }
}
