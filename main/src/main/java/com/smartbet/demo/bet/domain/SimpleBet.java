package com.smartbet.demo.bet.domain;

import com.google.common.collect.ImmutableList;
import com.smartbet.demo.fixtures.domain.Fixture;
import lombok.Data;

@Data
public class SimpleBet {
    private final Fixture fixture;
    private final ImmutableList<SelectedBetOutcome> outcomes;
}
