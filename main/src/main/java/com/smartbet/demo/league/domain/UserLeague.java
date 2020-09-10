package com.smartbet.demo.league.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class UserLeague {
    private final UUID userId;
    private final int leagueId;
}
