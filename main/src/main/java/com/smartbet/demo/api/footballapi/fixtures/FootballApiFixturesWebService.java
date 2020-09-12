package com.smartbet.demo.api.footballapi.fixtures;

import com.smartbet.demo.api.footballapi.fixtures.domain.FootballApiFixtureResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FootballApiFixturesWebService {
    @GET("fixtures/league/{leagueId}/next/20")
    Call<FootballApiFixtureResponse> getFixtures(@Path("leagueId") int leagueId);
}
