package com.smartbet.demo.grid.service;

import com.google.common.collect.ImmutableList;
import com.smartbet.demo.grid.domain.AdvancedBetGrid;
import com.smartbet.demo.grid.domain.BetGrid;
import org.springframework.stereotype.Service;

import static com.google.common.collect.ImmutableList.toImmutableList;

@Service
public class AdvancedGridsComputer {

    public ImmutableList<AdvancedBetGrid> computeAdvancedGrids(double betMoney, ImmutableList<BetGrid> grids) {
        int gridsCount = grids.size();
        double averageCombinedOdds = grids.stream().mapToDouble(BetGrid::getCombinedOdds).average().getAsDouble();
        return grids.stream()
                .map(grid -> compute(grid, gridsCount, betMoney, averageCombinedOdds))
                .collect(toImmutableList());
    }

    public AdvancedBetGrid compute(BetGrid grid, int gridsCount, double betMoney, double adjustedOdds) {
        double equitableMoneyBet = betMoney / gridsCount;
        double combinedOdds = grid.getCombinedOdds();
        double equitableGain = equitableMoneyBet * combinedOdds;
        double adjustedMoneyBet = (equitableMoneyBet * adjustedOdds) / combinedOdds;
        double adjustedMoneyGain = adjustedMoneyBet * combinedOdds;
        return new AdvancedBetGrid(grid.getId(), grid.getEntries(), combinedOdds,
                equitableMoneyBet, adjustedMoneyBet, equitableGain, adjustedMoneyGain);
    }
}
