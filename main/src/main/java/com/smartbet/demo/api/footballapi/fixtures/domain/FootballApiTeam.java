package com.smartbet.demo.api.footballapi.fixtures.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartbet.demo.team.domain.SimpleTeam;
import lombok.Data;

@Data
public class FootballApiTeam {
    @JsonProperty("team_id")
    private final int id;
    @JsonProperty("team_name")
    private final String name;
    private final String logoUrl;

    public SimpleTeam toDomain() {
        return new SimpleTeam(id, name, logoUrl);
    }
}
