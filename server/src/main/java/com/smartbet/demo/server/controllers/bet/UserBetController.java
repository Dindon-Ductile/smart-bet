package com.smartbet.demo.server.controllers.bet;

import com.google.common.collect.ImmutableList;
import com.smartbet.demo.bet.domain.UserCombinedBet;
import com.smartbet.demo.bet.domain.UserCombinedBetCreation;
import com.smartbet.demo.bet.domain.UserCombinedBetUpdate;
import com.smartbet.demo.bet.repository.UserCombinedBetsRepository;
import com.smartbet.demo.server.controllers.NotFoundException;
import com.smartbet.demo.user.UserId;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
public class UserBetController {
    private final UserCombinedBetsRepository userCombinedBetsRepository;

    public UserBetController(UserCombinedBetsRepository userCombinedBetsRepository) {
        this.userCombinedBetsRepository = userCombinedBetsRepository;
    }

    @GetMapping("/api/user/bets/{betId}")
    @ResponseStatus(OK)
    public UserCombinedBet getBet(@PathVariable("betId") UUID betId) throws NotFoundException {
        return userCombinedBetsRepository.findById(betId)
                .orElseThrow(NotFoundException::new);
    }

    @GetMapping("/api/user/bets")
    @ResponseStatus(OK)
    public ImmutableList<UserCombinedBet> getUserBets() {
        return userCombinedBetsRepository.findAllByUserId(UserId.userId);
    }

    @PostMapping("/api/user/bets")
    @ResponseStatus(CREATED)
    public UserCombinedBet createBet(@Valid @RequestBody WsUserBetInput creation) {
        UserCombinedBetCreation build =
                UserCombinedBetCreation.build(UserId.userId, creation.getEntries(), creation.getBetMoney());
        userCombinedBetsRepository.create(build);
        return build.toDomain();
    }

    @PutMapping("/api/user/bets/{betId}")
    @ResponseStatus(OK)
    public UserCombinedBet updateBet(@PathVariable("betId") UUID betId,
                                     @Valid @RequestBody WsUserBetInput update) throws NotFoundException {
        UserCombinedBet existingBet =
                userCombinedBetsRepository.findById(betId).orElseThrow(NotFoundException::new);
        UserCombinedBetUpdate build =
                UserCombinedBetUpdate.build(betId, update.getEntries(), update.getBetMoney());
        userCombinedBetsRepository.update(build);
        return build.toDomain(existingBet);
    }

    @DeleteMapping("/api/user/bets/{betId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteBet(@PathVariable("betId") UUID betId) {
        userCombinedBetsRepository.delete(betId);
    }
}
