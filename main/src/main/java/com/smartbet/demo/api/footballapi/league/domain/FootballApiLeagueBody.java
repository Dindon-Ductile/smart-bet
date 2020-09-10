package com.smartbet.demo.api.footballapi.league.domain;

import lombok.Data;

@Data
public class FootballApiLeagueBody {
    private final int results;
    private final FootballApiLeague[] leagues;
}
