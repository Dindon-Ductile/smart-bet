package com.smartbet.demo.api.footballapi.fixtures.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartbet.demo.fixtures.domain.RawFixture;
import com.smartbet.demo.fixtures.domain.SimpleLeague;
import lombok.Data;

import java.time.Instant;

@Data
public class FootballApiFixture {
    @JsonProperty("fixture_id")
    private final int fixtureId;
    @JsonProperty("league_id")
    private final int leagueId;
    private final FootballApiSimpleLeague league;
    @JsonProperty("event_timestamp")
    private final int eventTimeStamp;
    private final String venue;
    private final FootballApiTeam homeTeam;
    private final FootballApiTeam awayTeam;

    public RawFixture toDomain() {
        SimpleLeague simpleLeague = new SimpleLeague(leagueId, league.getName(),
                league.getCountry(), league.getLogoUrl(), league.getFlagUrl());
        return new RawFixture(fixtureId, simpleLeague, Instant.ofEpochSecond(eventTimeStamp), venue,
                homeTeam.toDomain(), awayTeam.toDomain());
    }
}
