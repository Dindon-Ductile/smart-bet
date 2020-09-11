package com.smartbet.demo.bet.domain;

import com.google.common.collect.ImmutableList;
import lombok.Data;

@Data
public class SimpleBet {
    private final int fixtureId;
    private final String fixtureName;
    private final ImmutableList<SelectedBetOutcome> outcomes;
}
