package com.smartbet.demo.grid.domain;

import com.google.common.collect.ImmutableList;
import com.smartbet.demo.fixtures.domain.Fixture;
import lombok.Data;

import java.util.UUID;

@Data
public class AdvancedBetGrid {
    private final UUID id;
    private final ImmutableList<BetGridEntry> entries;
    private final double combinedOdds;
    private final double equitableMoneyBet;
    private final double adjustedMoneyBet;
    private final double equitableGain;
    private final double adjustedGain;
}
