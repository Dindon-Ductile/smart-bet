package com.smartbet.demo.fixtures.domain;

import com.smartbet.demo.team.domain.SimpleTeam;
import lombok.Data;

import java.time.Instant;

@Data
public class Fixture {
    private final int fixtureId;
    private final int leagueId;
    private final Instant startsAt;
    private final String startsAtDay;
    private final String startsAtTime;
    private final String venue;
    private final SimpleTeam homeTeam;
    private final SimpleTeam awayTeam;
}
