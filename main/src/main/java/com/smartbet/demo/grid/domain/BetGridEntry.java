package com.smartbet.demo.grid.domain;

import com.smartbet.demo.bet.domain.BetOutcome;
import lombok.Data;

@Data
public class BetGridEntry {
    private final int fixtureId;
    private final String fixtureName;
    private final BetOutcome outcome;
    private final double odds;
}
