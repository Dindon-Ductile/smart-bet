package com.smartbet.demo.league.service;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.smartbet.demo.api.footballapi.league.FootballApiLeagueService;
import com.smartbet.demo.league.domain.League;
import com.smartbet.demo.league.domain.Leagues;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.google.common.collect.ImmutableList.toImmutableList;

@Service
public class AvailableLeaguesRetriever {
    private final FootballApiLeagueService footballApiLeagueService;
    private final UserLeagueMatcher userLeagueMatcher;

    public AvailableLeaguesRetriever(FootballApiLeagueService footballApiLeagueService,
                                     UserLeagueMatcher userLeagueMatcher) {
        this.footballApiLeagueService = footballApiLeagueService;
        this.userLeagueMatcher = userLeagueMatcher;
    }

    public Leagues getLeagues(LocalDate now) {
        ImmutableSet<League> allLeagues = footballApiLeagueService.getLeaguesFromYear(now.getYear());
        ImmutableList<League> matchedUserLeagues = userLeagueMatcher.getLeagues(allLeagues);
        ImmutableList<League> availableLeagues = filterOutLeaguesAlreadySelectedByUser(matchedUserLeagues, allLeagues);
        return new Leagues(matchedUserLeagues, availableLeagues);
    }

    public ImmutableList<League> getUserLeagues(LocalDate now) {
        ImmutableSet<League> allLeagues = footballApiLeagueService.getLeaguesFromYear(now.getYear());
        return userLeagueMatcher.getLeagues(allLeagues);
    }

    private ImmutableList<League> filterOutLeaguesAlreadySelectedByUser(ImmutableList<League> userLeagues,
                                                                        ImmutableCollection<League> allLeagues) {
        return allLeagues.stream()
                .filter(league -> !isAnUserLeague(userLeagues, league))
                .collect(toImmutableList());
    }

    private boolean isAnUserLeague(ImmutableList<League> userLeagues, League league) {
        return userLeagues.stream().anyMatch(userLeague -> userLeague.getId() == league.getId());
    }
}
