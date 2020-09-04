package com.smartbet.demo.api.footballapi.league;

import com.google.common.collect.ImmutableSet;
import com.smartbet.demo.CallExecutionException;
import com.smartbet.demo.CallExecutor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

import static com.google.common.collect.ImmutableSet.toImmutableSet;

@Service
public class FootballApiLeagueService {
    private final CallExecutor callExecutor;
    private final FootballApiLeagueWebService footballApiLeagueWebService;

    public FootballApiLeagueService(CallExecutor callExecutor, FootballApiLeagueWebService footballApiLeagueWebService) {
        this.callExecutor = callExecutor;
        this.footballApiLeagueWebService = footballApiLeagueWebService;
    }

    private ImmutableSet<FootballApiLeague> getLeaguesFromYear(int year) {
        try {
            FootballApiLeague[] leagues = callExecutor.execute(footballApiLeagueWebService.getLeagues(year));
            return Arrays.stream(leagues).collect(toImmutableSet());
        } catch (CallExecutionException | IOException e) {
            e.printStackTrace();
            return ImmutableSet.of();
        }
    }
}
