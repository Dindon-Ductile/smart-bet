package com.smartbet.demo.grid.service;

import com.google.common.collect.ImmutableList;
import com.smartbet.demo.bet.domain.CombinedBet;
import com.smartbet.demo.grid.domain.AdvancedBetGrid;
import com.smartbet.demo.grid.domain.BetGrid;
import com.smartbet.demo.grid.domain.BetGridEntry;
import com.smartbet.demo.grid.domain.CombinedBetResult;
import org.springframework.stereotype.Service;

@Service
public class CombinedBetResultComputer {
    private final BetEntriesComputer betEntriesComputer;
    private final BetGridsComputer betGridsComputer;
    private final AdvancedGridsComputer advancedGridsComputer;

    public CombinedBetResultComputer(BetEntriesComputer betEntriesComputer,
                                     BetGridsComputer betGridsComputer,
                                     AdvancedGridsComputer advancedGridsComputer) {
        this.betEntriesComputer = betEntriesComputer;
        this.betGridsComputer = betGridsComputer;
        this.advancedGridsComputer = advancedGridsComputer;
    }

    public CombinedBetResult compute(CombinedBet bet) {
        ImmutableList<ImmutableList<BetGridEntry>> selectedEntries = betEntriesComputer.computeSelectedEntries(bet);
        ImmutableList<BetGrid> grids = betGridsComputer.computeGrids(selectedEntries);
        ImmutableList<AdvancedBetGrid> advancedBetGrids =
                advancedGridsComputer.computeAdvancedGrids(bet.getBetMoney(), grids);
        return new CombinedBetResult(bet, advancedBetGrids);
    }


}
