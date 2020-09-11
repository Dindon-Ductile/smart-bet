package com.smartbet.demo.bet.domain;

import lombok.Data;

@Data
public class SelectedBetOutcome {
    private final BetOutcome outcome;
    private final double odds;
    private final boolean selected;
}
