package com.smartbet.demo.grid.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.smartbet.demo.bet.domain.CombinedBet;
import com.smartbet.demo.bet.domain.SelectedBetOutcome;
import com.smartbet.demo.bet.domain.SimpleBet;
import com.smartbet.demo.grid.domain.AdvancedBetGrid;
import com.smartbet.demo.grid.domain.BetGrid;
import com.smartbet.demo.grid.domain.BetGridEntry;
import com.smartbet.demo.grid.domain.CombinedBetResult;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.google.common.collect.ImmutableList.toImmutableList;

@Service
public class CombinedBetResultComputer {
    private final AdvancedGridComputer advancedGridComputer;

    public CombinedBetResultComputer(AdvancedGridComputer advancedGridComputer) {
        this.advancedGridComputer = advancedGridComputer;
    }

    public CombinedBetResult compute(CombinedBet bet) {
        ImmutableList<ImmutableList<BetGridEntry>> selectedEntries = computeSelectedEntries(bet);
        ImmutableList<BetGrid> grids = computeGrids(selectedEntries);
        int gridsCount = grids.size();
        ImmutableList<AdvancedBetGrid> advancedBetGrids = computeAdvancedGrids(gridsCount, bet.getBetMoney(), grids);
        return new CombinedBetResult(bet, advancedBetGrids);
    }

    private ImmutableList<AdvancedBetGrid> computeAdvancedGrids(int gridsCount, double betMoney, ImmutableList<BetGrid> grids) {
        return grids.stream()
                .map(grid -> advancedGridComputer.compute(grid, gridsCount, betMoney))
                .collect(toImmutableList());
    }

    private ImmutableList<BetGrid> computeGrids(ImmutableList<ImmutableList<BetGridEntry>> selectedEntries) {
        return Lists.cartesianProduct(selectedEntries).stream()
                .map(entries -> new BetGrid(UUID.randomUUID(), ImmutableList.copyOf(entries)))
                .collect(toImmutableList());
    }

    private ImmutableList<ImmutableList<BetGridEntry>> computeSelectedEntries(CombinedBet bet) {
        return bet.getEntries().stream()
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
