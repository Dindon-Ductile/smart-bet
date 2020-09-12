package com.smartbet.demo.api.footballapi.fixtures.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FootballApiSimpleLeague {
    private final String name;
    private final String country;
    @JsonProperty("logo")
    private final String logoUrl;
    @JsonProperty("flag")
    private final String flagUrl;
}
