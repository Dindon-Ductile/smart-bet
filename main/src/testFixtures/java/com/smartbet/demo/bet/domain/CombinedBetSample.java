package com.smartbet.demo.bet.domain;

import com.google.common.collect.ImmutableList;

import java.util.UUID;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static java.util.Arrays.stream;

public class CombinedBetSample {
    public static CombinedBet buildCombinedBet(SimpleBet... bets) {
        return new CombinedBet(ImmutableList.copyOf(bets), 50);
    }

    public static SimpleBet buildSimpleBet(BetOutcome... outComes) {
        return new SimpleBet(1, UUID.randomUUID().toString(), getCollect(outComes));
    }

    private static ImmutableList<SelectedBetOutcome> getCollect(BetOutcome[] outcome) {
        return stream(outcome)
                .map(o -> new SelectedBetOutcome(o, 1, true))
                .collect(toImmutableList());
    }
}
