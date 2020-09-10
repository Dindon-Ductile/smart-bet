package com.smartbet.demo.api.footballapi.league;

import com.smartbet.demo.api.footballapi.league.domain.FootballApiLeagueBody;
import com.smartbet.demo.api.footballapi.league.domain.FootballApiLeagueResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FootballApiLeagueWebService {
    @GET("leagues/season/{year}")
    Call<FootballApiLeagueResponse> getLeagues(@Path("year") Integer year);
}
