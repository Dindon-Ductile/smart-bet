package com.smartbet.demo.grid.service;

import com.google.common.collect.ImmutableList;
import com.smartbet.demo.bet.domain.CombinedBet;
import com.smartbet.demo.bet.domain.SelectedBetOutcome;
import com.smartbet.demo.bet.domain.SimpleBet;
import com.smartbet.demo.grid.domain.BetGridEntry;
import org.springframework.stereotype.Service;

import static com.google.common.collect.ImmutableList.toImmutableList;

@Service
public class BetEntriesComputer {
    public ImmutableList<ImmutableList<BetGridEntry>> computeSelectedEntries(CombinedBet bet) {
        return bet.getEntries().stream()
                .filter(SimpleBet::isActive)
                .map(this::filterOutUndesiredOutcomes)
                .collect(toImmutableList());
    }

    private ImmutableList<BetGridEntry> filterOutUndesiredOutcomes(SimpleBet entry) {
        return entry.getOutcomes().stream()
                .filter(SelectedBetOutcome::isSelected)
                .map(outcome -> new BetGridEntry(entry.getFixture().getFixtureId(), outcome.getOutcome(),
                        outcome.getOdds()))
                .collect(toImmutableList());
    }
}
