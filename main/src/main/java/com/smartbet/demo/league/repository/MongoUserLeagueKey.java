package com.smartbet.demo.league.repository;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class MongoUserLeagueKey implements Serializable {
    private final UUID userId;
    private final int leagueId;
}
