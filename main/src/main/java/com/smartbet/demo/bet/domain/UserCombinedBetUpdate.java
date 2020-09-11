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
public class UserCombinedBetUpdate {
    private final UUID id;
    private final ImmutableList<SimpleBet> entries;
    private final double betMoney;
    private final Optional<Instant> lastUpdatedAt;

    public static UserCombinedBetUpdate build(UUID id, ImmutableList<SimpleBet> entries, double betMoney) {
        return new UserCombinedBetUpdate(id, entries, betMoney, Optional.of(Instant.now()));
    }

    public UserCombinedBet toDomain(UserCombinedBet existingBet) {
        return new UserCombinedBet(existingBet.getId(), existingBet.getUserId(),
                entries, betMoney, existingBet.getCreatedAt(), lastUpdatedAt);
    }
}
