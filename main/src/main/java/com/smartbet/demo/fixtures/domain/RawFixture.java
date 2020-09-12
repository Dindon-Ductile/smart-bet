package com.smartbet.demo.fixtures.domain;

import com.smartbet.demo.team.domain.SimpleTeam;
import lombok.Data;

import java.time.Instant;

@Data
public class RawFixture {
    private final int fixtureId;
    private final SimpleLeague league;
    private final Instant startsAt;
    private final String venue;
    private final SimpleTeam homeTeam;
    private final SimpleTeam awayTeam;
}
