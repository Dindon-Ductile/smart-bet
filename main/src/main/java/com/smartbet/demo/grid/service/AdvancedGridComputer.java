package com.smartbet.demo.grid.service;

import com.smartbet.demo.grid.domain.AdvancedBetGrid;
import com.smartbet.demo.grid.domain.BetGrid;
import com.smartbet.demo.grid.domain.BetGridEntry;
import org.springframework.stereotype.Service;

@Service
public class AdvancedGridComputer {

    public AdvancedBetGrid compute(BetGrid grid, int gridsCount, double betMoney) {
        double combinedOdds = computeCombinedOdds(grid);
        double equitableMoneyBet = betMoney / gridsCount;
        double equitableGain = equitableMoneyBet * combinedOdds;
        return new AdvancedBetGrid(grid.getId(), grid.getEntries(), combinedOdds,
                equitableMoneyBet, equitableMoneyBet, equitableGain, equitableGain);
    }

    private double computeCombinedOdds(BetGrid grid) {
        return grid.getEntries().stream()
                .mapToDouble(BetGridEntry::getOdds)
                .reduce(1, (a, b) -> a * b);
    }
}
