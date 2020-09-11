package com.smartbet.demo.bet.repository;

import com.google.common.collect.ImmutableList;
import com.smartbet.demo.bet.domain.SimpleBet;
import com.smartbet.demo.bet.domain.UserCombinedBet;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@Document(collection = "userCombinedBets")
public class MongoUserCombinedBet {
    @Id
    private final UUID id;
    private final UUID userId;
    private final List<SimpleBet> entries;
    private final double betMoney;
    private final Instant createdAt;
    private final Optional<Instant> lastUpdatedAt;

    static MongoUserCombinedBet build(UserCombinedBet userCombinedBet) {
        return new MongoUserCombinedBet(userCombinedBet.getId(), userCombinedBet.getUserId(),
                userCombinedBet.getEntries(), userCombinedBet.getBetMoney(), userCombinedBet.getCreatedAt(),
                userCombinedBet.getLastUpdatedAt());
    }

    UserCombinedBet toDomain() {
        return new UserCombinedBet(id, userId, ImmutableList.copyOf(entries), betMoney, createdAt, lastUpdatedAt);
    }
}
