package com.smartbet.demo.server.controllers.bet;

import com.google.common.collect.ImmutableList;
import com.smartbet.demo.bet.domain.SimpleBet;
import lombok.Data;

@Data
@ValidWsUserBetInput
public class WsUserBetInput {
    private final ImmutableList<SimpleBet> entries;
    private final double betMoney;
}
