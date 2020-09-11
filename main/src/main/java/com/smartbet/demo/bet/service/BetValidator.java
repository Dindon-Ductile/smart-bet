package com.smartbet.demo.bet.service;

import com.google.common.collect.ImmutableCollection;
import com.smartbet.demo.bet.domain.SelectedBetOutcome;
import com.smartbet.demo.bet.domain.SimpleBet;
import org.springframework.stereotype.Service;

@Service
public class BetValidator {
    public boolean verifyBets(ImmutableCollection<SimpleBet> bets) {
        return bets.stream().allMatch(bet -> verifyBet(bet));
    }

    private boolean verifyBet(SimpleBet bet) {
        return sizeConstraint(bet) && atLeastOneSelected(bet) &&
                allDifferentOutcomes(bet) && oddsGreaterThanZero(bet);
    }

    private boolean oddsGreaterThanZero(SimpleBet bet) {
        return bet.getOutcomes().stream().allMatch(o -> o.getOdds() > 0);
    }

    private boolean allDifferentOutcomes(SimpleBet bet) {
        long differentOutcomesCount = bet.getOutcomes().stream()
                .map(SelectedBetOutcome::getOutcome)
                .distinct()
                .count();
        return differentOutcomesCount == bet.getOutcomes().size();
    }

    private boolean atLeastOneSelected(SimpleBet bet) {
        return bet.getOutcomes().stream().anyMatch(SelectedBetOutcome::isSelected);
    }

    private boolean sizeConstraint(SimpleBet bet) {
        return bet.getOutcomes().size() == 3;
    }
}
