package com.smartbet.demo.fixtures.domain;

import com.google.common.collect.ImmutableList;
import lombok.Data;

@Data
public class FixturesByDate {
    private final String date;
    private final ImmutableList<Fixture> fixtures;
}
