package com.smartbet.demo.grid.domain;

import com.google.common.collect.ImmutableList;
import com.smartbet.demo.bet.domain.CombinedBet;
import lombok.Data;

@Data
public class CombinedBetResult {
    private final CombinedBet bet;
    private final ImmutableList<AdvancedBetGrid> playableGrids;
    private final double actualMoneyAllocatedAmongstGrids;
}
