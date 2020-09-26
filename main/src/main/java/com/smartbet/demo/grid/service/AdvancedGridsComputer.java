package com.smartbet.demo.grid.service;

import com.google.common.collect.ImmutableList;
import com.smartbet.demo.grid.domain.AdvancedBetGrid;
import com.smartbet.demo.grid.domain.BetGridMoneyOverview;
import com.smartbet.demo.grid.domain.BetGrid;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.google.common.collect.ImmutableList.toImmutableList;

@Service
public class AdvancedGridsComputer {
    private final ComboBoosterComputer comboBoosterComputer;
    private final LcmComputer lcmComputer;

    public AdvancedGridsComputer(ComboBoosterComputer comboBoosterComputer, LcmComputer lcmComputer) {
        this.comboBoosterComputer = comboBoosterComputer;
        this.lcmComputer = lcmComputer;
    }

    public ImmutableList<AdvancedBetGrid> computeAdvancedGrids(double betMoney, ImmutableList<BetGrid> grids) {
        int gridsCount = grids.size();
        ImmutableList<Double> adjustedMoneyMultiplicators = computeAdjustedMoneyMultiplicatorPerGrid(grids);
        return IntStream.range(0, gridsCount)
                .mapToObj(i -> compute(grids.get(i), gridsCount, betMoney, adjustedMoneyMultiplicators.get(i)))
                .collect(toImmutableList());
    }

    private ImmutableList<Double> computeAdjustedMoneyMultiplicatorPerGrid(ImmutableList<BetGrid> grids) {
        BigInteger lcm = computeLcm(grids);
        List<BigInteger> dividends = getCombinedOdds(grids).stream()
                .map(n -> lcm.divide(new BigInteger(String.valueOf(n))))
                .collect(toImmutableList());
        BigInteger dividensSum = dividends.stream().reduce(BigInteger::add).get();
        return dividends.stream()
                .map(dividend -> new BigDecimal(dividend).divide(new BigDecimal(dividensSum), 10, RoundingMode.HALF_UP))
                .map(BigDecimal::doubleValue)
                .collect(toImmutableList());
    }


    public BigInteger computeLcm(ImmutableList<BetGrid> grids) {
        List<Integer> collect = getCombinedOdds(grids);
        return lcmComputer.computeLcmOfIntegerList(collect);
    }

    private List<Integer> getCombinedOdds(ImmutableList<BetGrid> grids) {
        return grids.stream()
                .map(grid -> (int) Math.round(grid.getCombinedOdds()))
                .collect(Collectors.toList());
    }

    public AdvancedBetGrid compute(BetGrid grid, int gridsCount, double betMoney, double adjustedMoneyMultiplicator) {
        double comboBoosterPercentage = comboBoosterComputer.computeComboBooster(grid);
        BetGridMoneyOverview equitableBetGridMoneyOverview = computeEquitableBetData(grid, betMoney, gridsCount, comboBoosterPercentage);
        BetGridMoneyOverview adjustedBetGridMoneyOverview = computeAdjustedBetData(grid, betMoney, adjustedMoneyMultiplicator, comboBoosterPercentage);
        return new AdvancedBetGrid(grid.getId(), grid.getEntries(), equitableBetGridMoneyOverview, adjustedBetGridMoneyOverview);
    }

    public BetGridMoneyOverview computeEquitableBetData(BetGrid grid, double totalMoney, int gridsCount, double comboBoosterPercentage) {
        double moneyBet = Rounder.roundWith2Decimals(totalMoney / gridsCount);
        double combinedOdds = grid.getCombinedOdds();
        double baseGain = Rounder.roundWith2Decimals(moneyBet * combinedOdds);
        double comboBoosterGain = Rounder.roundWith2Decimals(baseGain * comboBoosterPercentage / 100);
        double totalGain = baseGain + comboBoosterGain;
        return new BetGridMoneyOverview(moneyBet, comboBoosterPercentage, baseGain, comboBoosterGain, totalGain, combinedOdds);
    }

    public BetGridMoneyOverview computeAdjustedBetData(BetGrid grid, double betMoney, double adjustedMoneyMultiplicator,
                                                       double comboBoosterPercentage) {
        double gridOdds = grid.getCombinedOdds();
        double moneyBet = betMoney * adjustedMoneyMultiplicator;
        double roundedMoneyBet = Rounder.roundWith2Decimals(moneyBet);
        double baseGain = Rounder.roundWith2Decimals(roundedMoneyBet * gridOdds);
        double comboBoosterGain = Rounder.roundWith2Decimals(baseGain * comboBoosterPercentage / 100);
        double totalGain = baseGain + comboBoosterGain;
        return new BetGridMoneyOverview(roundedMoneyBet, comboBoosterPercentage, baseGain, comboBoosterGain, totalGain, gridOdds);
    }
}
