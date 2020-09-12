package com.smartbet.demo.api.footballapi.fixtures;

import com.google.common.collect.ImmutableSet;
import com.smartbet.demo.CallExecutionException;
import com.smartbet.demo.CallExecutor;
import com.smartbet.demo.api.footballapi.fixtures.domain.FootballApiFixture;
import com.smartbet.demo.api.footballapi.fixtures.domain.FootballApiFixtureResponse;
import com.smartbet.demo.fixtures.domain.RawFixture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;
import java.util.Arrays;

import static com.google.common.collect.ImmutableSet.toImmutableSet;

@Service
@Slf4j
public class FootballApiFixturesService {
    private final FootballApiFixturesWebService footballApiFixturesWebService;
    private final CallExecutor callExecutor;

    public FootballApiFixturesService(FootballApiFixturesWebService footballApiFixturesWebService, CallExecutor callExecutor) {
        this.footballApiFixturesWebService = footballApiFixturesWebService;
        this.callExecutor = callExecutor;
    }

    public ImmutableSet<RawFixture> getLeagueNextFixtures(int leagueId) {
        try {
            Call<FootballApiFixtureResponse> call = footballApiFixturesWebService.getFixtures(leagueId);
            FootballApiFixtureResponse response = callExecutor.execute(call);
            return Arrays.stream(response.getApi().getFixtures())
                    .map(FootballApiFixture::toDomain)
                    .collect(toImmutableSet());
        } catch (CallExecutionException | IOException e) {
            log.warn("Could not retrieve leagues", e);
            return ImmutableSet.of();
        }
    }
}
