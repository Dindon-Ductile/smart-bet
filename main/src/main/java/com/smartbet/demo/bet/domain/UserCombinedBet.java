package com.smartbet.demo.bet.domain;

import com.google.common.collect.ImmutableList;
import lombok.Data;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Data
public class UserCombinedBet {
    private final UUID id;
    private final UUID userId;
    private final ImmutableList<SimpleBet> entries;
    private final double betMoney;
    private final Instant createdAt;
    private final Optional<Instant> lastUpdatedAt;

    public CombinedBet toCombinedBet() {
        return new CombinedBet(entries, betMoney);
    }
}
