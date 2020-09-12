package com.smartbet.demo.server.controllers.bet;

import com.google.common.collect.ImmutableSet;
import com.smartbet.demo.fixtures.domain.Fixture;
import com.smartbet.demo.grid.domain.CombinedBetResult;
import lombok.Data;

@Data
public class WsUserBetGrids {
    private final ImmutableSet<Fixture> fixtures;
    private final CombinedBetResult combinedBetResult;
}
