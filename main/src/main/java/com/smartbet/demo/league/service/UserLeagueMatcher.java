package com.smartbet.demo.league.service;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.smartbet.demo.league.domain.League;
import com.smartbet.demo.league.domain.UserLeague;
import com.smartbet.demo.league.repository.UserLeaguesRepository;
import com.smartbet.demo.user.UserId;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.google.common.collect.ImmutableList.toImmutableList;

@Service
public class UserLeagueMatcher {
    private final UserLeaguesRepository userLeaguesRepository;

    public UserLeagueMatcher(UserLeaguesRepository userLeaguesRepository) {
        this.userLeaguesRepository = userLeaguesRepository;
    }

    public ImmutableList<League> getLeagues(ImmutableCollection<League> allLeagues) {
        ImmutableSet<UserLeague> userLeagues = userLeaguesRepository.findUserLeagues(UserId.userId);
        return matchUserLeagues(allLeagues, userLeagues);
    }

    private ImmutableList<League> matchUserLeagues(ImmutableCollection<League> allLeagues, ImmutableSet<UserLeague> userLeagues1) {
        return userLeagues1.stream()
                .map(userLeague -> this.match(userLeague, allLeagues))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toImmutableList());
    }

    private Optional<League> match(UserLeague userLeague, ImmutableCollection<League> leagues) {
        return leagues.stream().filter(league -> league.getId() == userLeague.getLeagueId()).findFirst();
    }
}
