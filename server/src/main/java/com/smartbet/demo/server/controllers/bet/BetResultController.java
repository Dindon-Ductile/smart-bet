package com.smartbet.demo.server.controllers.bet;

import com.google.common.collect.ImmutableSet;
import com.smartbet.demo.bet.domain.CombinedBet;
import com.smartbet.demo.bet.domain.SimpleBet;
import com.smartbet.demo.bet.domain.UserCombinedBet;
import com.smartbet.demo.bet.repository.UserCombinedBetsRepository;
import com.smartbet.demo.fixtures.domain.Fixture;
import com.smartbet.demo.grid.domain.CombinedBetResult;
import com.smartbet.demo.grid.service.CombinedBetResultComputer;
import com.smartbet.demo.server.controllers.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.google.common.collect.ImmutableSet.toImmutableSet;

@RestController
public class BetResultController {
    private final CombinedBetResultComputer combinedBetResultComputer;
    private final UserCombinedBetsRepository userCombinedBetsRepository;

    public BetResultController(CombinedBetResultComputer combinedBetResultComputer,
                               UserCombinedBetsRepository userCombinedBetsRepository) {
        this.combinedBetResultComputer = combinedBetResultComputer;
        this.userCombinedBetsRepository = userCombinedBetsRepository;
    }

    @GetMapping("/api/user/bets/{betId}/grids")
    public WsUserBetGrids getUserBetGrids(@PathVariable UUID betId) throws NotFoundException {
        UserCombinedBet userCombinedBet =
                userCombinedBetsRepository.findById(betId).orElseThrow(NotFoundException::new);
        CombinedBet combinedBet = userCombinedBet.toCombinedBet();
        ImmutableSet<Fixture> fixtures = getFixtures(userCombinedBet);
        CombinedBetResult combinedBetResult = combinedBetResultComputer.compute(combinedBet);
        return new WsUserBetGrids(fixtures, combinedBetResult);
    }

    private ImmutableSet<Fixture> getFixtures(UserCombinedBet userCombinedBet) {
        return userCombinedBet.getEntries().stream().map(SimpleBet::getFixture)
                .collect(toImmutableSet());
    }
}
