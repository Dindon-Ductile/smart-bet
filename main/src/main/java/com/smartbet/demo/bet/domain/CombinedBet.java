package com.smartbet.demo.bet.domain;

import com.google.common.collect.ImmutableList;
import lombok.Data;

@Data
public class CombinedBet {
    private final ImmutableList<SimpleBet> entries;
    private final double betMoney;
}
