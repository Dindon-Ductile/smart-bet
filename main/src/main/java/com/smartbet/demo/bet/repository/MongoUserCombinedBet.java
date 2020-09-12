package com.smartbet.demo.bet.repository;

import com.google.common.collect.ImmutableList;
import com.smartbet.demo.bet.domain.SimpleBet;
import com.smartbet.demo.bet.domain.UserCombinedBet;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.collect.ImmutableList.toImmutableList;

@Data
@Document(collection = "userCombinedBets")
class MongoUserCombinedBet {
    @Id
    private final UUID id;
    private final UUID userId;
    private final List<MongoSimpleBet> entries;
    private final double betMoney;
    private final Instant createdAt;
    private final Optional<Instant> lastUpdatedAt;

    static MongoUserCombinedBet build(UserCombinedBet userCombinedBet) {
        List<MongoSimpleBet> entries = userCombinedBet.getEntries().stream()
                .map(MongoSimpleBet::build)
                .collect(Collectors.toList());
        return new MongoUserCombinedBet(userCombinedBet.getId(), userCombinedBet.getUserId(),
                entries, userCombinedBet.getBetMoney(), userCombinedBet.getCreatedAt(),
                userCombinedBet.getLastUpdatedAt());
    }

    UserCombinedBet toDomain() {
        ImmutableList<SimpleBet> entries = this.entries.stream()
                .map(MongoSimpleBet::toDomain)
                .collect(toImmutableList());
        return new UserCombinedBet(id, userId, entries, betMoney, createdAt, lastUpdatedAt);
    }
}
