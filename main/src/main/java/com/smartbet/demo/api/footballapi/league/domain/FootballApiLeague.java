package com.smartbet.demo.api.footballapi.league.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartbet.demo.country.domain.Country;
import com.smartbet.demo.league.domain.League;
import lombok.Data;

import java.time.LocalDate;
import java.util.Optional;

@Data
public class FootballApiLeague {
    @JsonProperty("league_id")
    private final int leagueId;
    private final String name;
    @JsonProperty("logo")
    private final String logoUrl;
    private final String country;
    @JsonProperty("country_code")
    private final String countryCode;
    @JsonProperty("flag")
    private final String countryFlag;
    @JsonProperty("is_current")
    private final boolean isCurrent;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("season_start")
    private final LocalDate seasonStart;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("season_end")
    private final LocalDate seasonEnd;

    public League toLeague() {
        Country country = new Country(Optional.ofNullable(countryCode), this.country, Optional.ofNullable(countryFlag));
        return new League(leagueId, name, logoUrl, country, isCurrent, seasonStart, seasonEnd);
    }
}
