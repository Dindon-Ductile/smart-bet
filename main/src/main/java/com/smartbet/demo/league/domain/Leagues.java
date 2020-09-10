package com.smartbet.demo.league.domain;

import com.google.common.collect.ImmutableList;
import lombok.Data;

@Data
public class Leagues {
    private final ImmutableList<League> userLeagues;
    private final ImmutableList<League> availableLeagues;
}
