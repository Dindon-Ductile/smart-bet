package com.smartbet.demo.league.domain;

import com.smartbet.demo.country.domain.Country;
import lombok.Data;

import java.time.LocalDate;

@Data
public class League {
    private final int id;
    private final String name;
    private final String logoUrl;
    private final Country country;
    private final boolean isCurrent;
    private final LocalDate seasonStart;
    private final LocalDate seasonEnd;
}
