package com.smartbet.demo.fixtures.domain;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import lombok.Data;

@Data
public class UserLeaguesFixtures {
    private final ImmutableList<FixturesByDate> fixturesByDate;
    private final ImmutableSet<SimpleLeague> leagues;
}
