package com.smartbet.demo.api.footballapi.league;

import com.google.common.collect.ImmutableSet;
import com.smartbet.demo.CallExecutionException;
import com.smartbet.demo.CallExecutor;
import com.smartbet.demo.api.footballapi.league.domain.FootballApiLeague;
import com.smartbet.demo.api.footballapi.league.domain.FootballApiLeagueBody;
import com.smartbet.demo.api.footballapi.league.domain.FootballApiLeagueResponse;
import com.smartbet.demo.league.domain.League;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;
import java.util.Arrays;

import static com.google.common.collect.ImmutableSet.toImmutableSet;

@Service
@Slf4j
public class FootballApiLeagueService {
    private final CallExecutor callExecutor;
    private final FootballApiLeagueWebService footballApiLeagueWebService;

    public FootballApiLeagueService(CallExecutor callExecutor, FootballApiLeagueWebService footballApiLeagueWebService) {
        this.callExecutor = callExecutor;
        this.footballApiLeagueWebService = footballApiLeagueWebService;
    }

    public ImmutableSet<League> getLeaguesFromYear(int year) {
        try {
            Call<FootballApiLeagueResponse> call = footballApiLeagueWebService.getLeagues(year);
            FootballApiLeagueResponse response = callExecutor.execute(call);
            return Arrays.stream(response.getApi().getLeagues())
                    .map(FootballApiLeague::toLeague).collect(toImmutableSet());
        } catch (CallExecutionException | IOException e) {
            log.warn("Could not retrieve leagues", e);
            return ImmutableSet.of();
        }
    }
}
