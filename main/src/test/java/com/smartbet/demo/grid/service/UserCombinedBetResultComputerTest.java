package com.smartbet.demo.grid.service;

import com.google.common.collect.ImmutableList;
import com.smartbet.demo.bet.domain.CombinedBet;
import com.smartbet.demo.bet.domain.SelectedBetOutcome;
import com.smartbet.demo.bet.domain.SimpleBet;
import com.smartbet.demo.grid.domain.CombinedBetResult;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static com.smartbet.demo.bet.domain.BetOutcome.*;
import static com.smartbet.demo.bet.domain.CombinedBetSample.buildCombinedBet;
import static com.smartbet.demo.bet.domain.CombinedBetSample.buildSimpleBet;

@Ignore
public class UserCombinedBetResultComputerTest {
    private CombinedBetResultComputer combinedBetResultComputer;

    @Before
    public void setUp() {
        this.combinedBetResultComputer = new CombinedBetResultComputer(new AdvancedGridComputer());
    }

    @Test
    public void test1() {
        CombinedBet bet = buildCombinedBet(
                buildSimpleBet(HOME, NULL),
                buildSimpleBet(NULL, AWAY)
        );
        CombinedBetResult compute = combinedBetResultComputer.compute(bet);

        Assertions.assertThat(compute).isNull();
    }

    @Test
    public void test2() {
        CombinedBet bet = buildCombinedBet(
                new SimpleBet(1, "a", ImmutableList.of(
                        new SelectedBetOutcome(HOME, 2.5, true),
                        new SelectedBetOutcome(NULL, 3, false),
                        new SelectedBetOutcome(AWAY, 7.1, true))),
                new SimpleBet(2, "b", ImmutableList.of(
                        new SelectedBetOutcome(HOME, 6, false),
                        new SelectedBetOutcome(NULL, 1.9, true),
                        new SelectedBetOutcome(AWAY, 3.4, true)))
        );
        CombinedBetResult compute = combinedBetResultComputer.compute(bet);

        Assertions.assertThat(compute).isNull();
    }
}