package com.smartbet.demo.server.controllers.leagues;

import com.google.common.collect.ImmutableList;
import com.smartbet.demo.league.domain.League;
import com.smartbet.demo.league.domain.UserLeague;
import com.smartbet.demo.league.repository.UserLeaguesRepository;
import com.smartbet.demo.league.service.AvailableLeaguesRetriever;
import com.smartbet.demo.user.UserId;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.time.LocalDate.now;

@RestController
public class UserLeagueController {
    private final UserLeaguesRepository userLeaguesRepository;
    private final AvailableLeaguesRetriever availableLeaguesRetriever;

    public UserLeagueController(UserLeaguesRepository userLeaguesRepository,
                                AvailableLeaguesRetriever availableLeaguesRetriever) {
        this.userLeaguesRepository = userLeaguesRepository;
        this.availableLeaguesRetriever = availableLeaguesRetriever;
    }

    @GetMapping("/api/user/leagues")
    public ImmutableList<League> getUserLeagues() {
        return availableLeaguesRetriever.getUserLeagues(now());
    }

    @PostMapping("/api/user/leagues")
    public void createUserLeague(@Valid @RequestBody LeagueId leagueId) {
        userLeaguesRepository.createUserLeague(new UserLeague(UserId.userId, leagueId.getLeagueId()));
    }

    @DeleteMapping("/api/user/leagues/{leagueId}")
    public void removeUserLeague(@PathVariable int leagueId) {
        userLeaguesRepository.deleteUserLeague(UserId.userId, leagueId);
    }
}
