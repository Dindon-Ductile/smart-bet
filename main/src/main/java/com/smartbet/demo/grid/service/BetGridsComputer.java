package com.smartbet.demo.grid.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.smartbet.demo.grid.domain.BetGrid;
import com.smartbet.demo.grid.domain.BetGridEntry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.google.common.collect.ImmutableList.toImmutableList;

@Service
public class BetGridsComputer {
    public ImmutableList<BetGrid> computeGrids(ImmutableList<ImmutableList<BetGridEntry>> selectedEntries) {
        return Lists.cartesianProduct(selectedEntries).stream()
                .map(entries -> computeBetGrid(entries))
                .collect(toImmutableList());
    }

    private BetGrid computeBetGrid(List<BetGridEntry> entries) {
        ImmutableList<BetGridEntry> immutableEntries = ImmutableList.copyOf(entries);
        double combinedOdds = computeCombinedOdds(immutableEntries);
        return new BetGrid(UUID.randomUUID(), immutableEntries, combinedOdds);
    }

    private double computeCombinedOdds(ImmutableList<BetGridEntry> entries) {
        double baseCombinedOdds = entries.stream()
                .mapToDouble(BetGridEntry::getOdds)
                .reduce(1, (a, b) -> a * b);
        return Rounder.roundWith2Decimals(baseCombinedOdds);
    }
}
