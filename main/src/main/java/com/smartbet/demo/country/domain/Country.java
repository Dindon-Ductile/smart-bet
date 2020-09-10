package com.smartbet.demo.country.domain;

import lombok.Data;

import java.util.Optional;

@Data
public class Country {
    private final Optional<String> id;
    private final String name;
    private final Optional<String> flagUrl;
}
