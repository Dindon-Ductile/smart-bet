package com.smartbet.demo.grid.domain;

import lombok.Data;

@Data
public class BetGridMoneyOverview {
    private final double moneyBet;
    private final double comboBoosterPercentage;
    private final double baseGain;
    private final double comboBoosterGain;
    private final double totalGain;
    private final double combinedOdds;
}
