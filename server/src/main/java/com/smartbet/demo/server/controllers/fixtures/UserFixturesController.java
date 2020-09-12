package com.smartbet.demo.server.controllers.fixtures;

import com.smartbet.demo.fixtures.domain.UserLeaguesFixtures;
import com.smartbet.demo.fixtures.service.UserLeagueFixturesRetriever;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserFixturesController {
    private final UserLeagueFixturesRetriever userLeagueFixturesRetriever;

    public UserFixturesController(UserLeagueFixturesRetriever userLeagueFixturesRetriever) {
        this.userLeagueFixturesRetriever = userLeagueFixturesRetriever;
    }

    @GetMapping("/api/user/leagues/fixtures")
    public UserLeaguesFixtures getFixtures() {
        return this.userLeagueFixturesRetriever.retrieveUserFixtures();
    }
}
