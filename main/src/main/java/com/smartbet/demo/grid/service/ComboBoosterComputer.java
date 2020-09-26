package com.smartbet.demo.grid.service;

import com.smartbet.demo.grid.domain.BetGrid;
import org.springframework.stereotype.Service;

@Service
public class ComboBoosterComputer {

    public double computeComboBooster(BetGrid betGrid) {
        long validEntries = countValidEntries(betGrid);
        return computePercentage(validEntries);
    }

    public double computePercentage(long validEntries) {
        if (validEntries <= 3) {
            return 0;
        } else if (validEntries <= 9) {
            return (validEntries - 3) * 2.5d;
        } else if (validEntries <= 14) {
            return 15 + (validEntries - 9) * 5d;
        } else {
            return Math.min(100, 40 + (validEntries - 14) * 10d);
        }
    }

    private long countValidEntries(BetGrid betGrid) {
        return betGrid.getEntries().stream()
                .filter(betGridEntry -> betGridEntry.getOdds() > 1.10d)
                .count();
    }
}
