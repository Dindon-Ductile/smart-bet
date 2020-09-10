package com.smartbet.demo.server.controllers.leagues;

import com.smartbet.demo.league.domain.Leagues;
import com.smartbet.demo.league.service.AvailableLeaguesRetriever;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class LeagueController {
    private final AvailableLeaguesRetriever availableLeaguesRetriever;


    public LeagueController(AvailableLeaguesRetriever availableLeaguesRetriever) {
        this.availableLeaguesRetriever = availableLeaguesRetriever;
    }

    @GetMapping("/api/leagues/available")
    public Leagues getAvailableLeagues() {
        LocalDate now = LocalDate.now();
        return availableLeaguesRetriever.getLeagues(now);
    }
}
