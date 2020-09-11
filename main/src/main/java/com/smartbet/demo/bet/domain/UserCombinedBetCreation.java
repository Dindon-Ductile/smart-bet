package com.smartbet.demo.bet.domain;

import com.google.common.collect.ImmutableList;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCombinedBetCreation {
    private final UUID id;
    private final UUID userId;
    private final ImmutableList<SimpleBet> entries;
    private final double betMoney;
    private final Instant createdAt;
    private final Optional<Instant> lastUpdatedAt;

    public static UserCombinedBetCreation build(UUID userId, ImmutableList<SimpleBet> entries, double betMoney) {
        return new UserCombinedBetCreation(UUID.randomUUID(), userId, entries, betMoney, Instant.now(), Optional.empty());
    }

    public UserCombinedBet toDomain() {
        return new UserCombinedBet(id, userId, entries, betMoney, createdAt, lastUpdatedAt);
    }
}
