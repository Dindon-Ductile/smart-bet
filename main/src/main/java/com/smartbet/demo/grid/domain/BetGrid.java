package com.smartbet.demo.grid.domain;

import com.google.common.collect.ImmutableList;
import lombok.Data;

import java.util.UUID;

@Data
public class BetGrid {
    private final UUID id;
    private final ImmutableList<BetGridEntry> entries;
    private final double combinedOdds;
}
