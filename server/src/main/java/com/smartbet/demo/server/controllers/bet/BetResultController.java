package com.smartbet.demo.server.controllers.bet;

import com.smartbet.demo.bet.domain.CombinedBet;
import com.smartbet.demo.bet.domain.UserCombinedBet;
import com.smartbet.demo.bet.repository.UserCombinedBetsRepository;
import com.smartbet.demo.grid.domain.CombinedBetResult;
import com.smartbet.demo.grid.service.CombinedBetResultComputer;
import com.smartbet.demo.server.controllers.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class BetResultController {
    private final CombinedBetResultComputer combinedBetResultComputer;
    private final UserCombinedBetsRepository userCombinedBetsRepository;

    public BetResultController(CombinedBetResultComputer combinedBetResultComputer,
                               UserCombinedBetsRepository userCombinedBetsRepository) {
        this.combinedBetResultComputer = combinedBetResultComputer;
        this.userCombinedBetsRepository = userCombinedBetsRepository;
    }

    @GetMapping("/api/user/bets/{betId}/result")
    public CombinedBetResult getResult(@PathVariable UUID betId) throws NotFoundException {
        UserCombinedBet userCombinedBet =
                userCombinedBetsRepository.findById(betId).orElseThrow(NotFoundException::new);
        CombinedBet combinedBet = userCombinedBet.toCombinedBet();
        return combinedBetResultComputer.compute(combinedBet);
    }
}
