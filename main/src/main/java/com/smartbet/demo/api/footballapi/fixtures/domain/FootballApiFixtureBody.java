package com.smartbet.demo.api.footballapi.fixtures.domain;

import lombok.Data;

@Data
public class FootballApiFixtureBody {
    private final int results;
    private final FootballApiFixture[] fixtures;
}
