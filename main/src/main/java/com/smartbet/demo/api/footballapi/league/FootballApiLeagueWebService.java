package com.smartbet.demo.api.footballapi.league;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FootballApiLeagueWebService {
    @GET("leagues/season/{year}")
    Call<FootballApiLeague[]> getLeagues(@Path("year") Integer year);
}
