package com.smartbet.demo.bet.repository;

import com.google.common.collect.ImmutableList;
import com.smartbet.demo.bet.domain.SelectedBetOutcome;
import com.smartbet.demo.bet.domain.SimpleBet;
import com.smartbet.demo.fixtures.domain.Fixture;
import lombok.Data;

import java.util.List;

@Data
class MongoSimpleBet {
    private final Fixture fixture;
    private final List<SelectedBetOutcome> outcomes;

    static MongoSimpleBet build(SimpleBet bet) {
        return new MongoSimpleBet(bet.getFixture(), bet.getOutcomes());
    }

    SimpleBet toDomain() {
        return new SimpleBet(fixture, ImmutableList.copyOf(outcomes));
    }
}
