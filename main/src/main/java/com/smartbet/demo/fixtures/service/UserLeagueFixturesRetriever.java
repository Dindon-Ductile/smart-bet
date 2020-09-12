package com.smartbet.demo.fixtures.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.smartbet.demo.api.footballapi.fixtures.FootballApiFixturesService;
import com.smartbet.demo.fixtures.domain.*;
import com.smartbet.demo.league.domain.UserLeague;
import com.smartbet.demo.league.repository.UserLeaguesRepository;
import com.smartbet.demo.user.UserId;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Locale;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static com.google.common.collect.ImmutableSet.toImmutableSet;
import static java.util.Locale.FRENCH;

@Service
public class UserLeagueFixturesRetriever {
    private final UserLeaguesRepository userLeaguesRepository;
    private final FootballApiFixturesService footballApiFixturesService;

    public UserLeagueFixturesRetriever(UserLeaguesRepository userLeaguesRepository,
                                       FootballApiFixturesService footballApiFixturesService) {
        this.userLeaguesRepository = userLeaguesRepository;
        this.footballApiFixturesService = footballApiFixturesService;
    }

    public UserLeaguesFixtures retrieveUserFixtures() {
        ImmutableSet<UserLeague> userLeagues = this.userLeaguesRepository.findUserLeagues(UserId.userId);
        ImmutableList<RawFixture> userNextRawFixtures = retrieveRawFixtures(userLeagues);
        ImmutableSet<SimpleLeague> leagues = extractLeagues(userNextRawFixtures);
        ImmutableList<LocalDate> dates = computeDistinctDates(userNextRawFixtures);
        ImmutableList<FixturesByDate> fixturesByDates = dates.stream()
                .map(date -> computeFixturesByDate(userNextRawFixtures, date))
                .collect(toImmutableList());
        return new UserLeaguesFixtures(fixturesByDates, leagues);
    }

    private FixturesByDate computeFixturesByDate(ImmutableList<RawFixture> userNextRawFixtures, LocalDate date) {
        return new FixturesByDate(formatDate(date), userNextRawFixtures.stream()
                .filter(fixture -> getFixtureDate(fixture).equals(date))
                .map(this::convertRawFixtureToFixture)
                .collect(toImmutableList()));
    }

    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("EEEE d MMM", Locale.FRENCH));
    }

    private ImmutableSet<SimpleLeague> extractLeagues(ImmutableList<RawFixture> userNextRawFixtures) {
        return userNextRawFixtures.stream().map(RawFixture::getLeague).collect(toImmutableSet());
    }

    private Fixture convertRawFixtureToFixture(RawFixture fixture) {
        return new Fixture(fixture.getFixtureId(), fixture.getLeague().getId(),
                fixture.getStartsAt(), formatDate(getFixtureDate(fixture)), formatFixtureTime(fixture),
                fixture.getVenue(), fixture.getHomeTeam(), fixture.getAwayTeam());
    }

    private String formatFixtureTime(RawFixture fixture) {
        return getFixtureZonedDateTime(fixture).toLocalTime()
                .format(DateTimeFormatter.ofPattern("HH:mm", FRENCH));
    }

    private ImmutableList<LocalDate> computeDistinctDates(ImmutableList<RawFixture> userNextRawFixtures) {
        return userNextRawFixtures.stream()
                .map(this::getFixtureDate)
                .distinct()
                .sorted(LocalDate::compareTo)
                .collect(toImmutableList());
    }

    private LocalDate getFixtureDate(RawFixture rawFixture) {
        return getFixtureZonedDateTime(rawFixture).toLocalDate();
    }

    private ZonedDateTime getFixtureZonedDateTime(RawFixture rawFixture) {
        return rawFixture.getStartsAt().atZone(ZoneId.systemDefault());
    }

    private ImmutableList<RawFixture> retrieveRawFixtures(ImmutableSet<UserLeague> userLeagues) {
        return userLeagues.stream()
                .map(league -> footballApiFixturesService.getLeagueNextFixtures(league.getLeagueId()))
                .flatMap(Collection::stream)
                .collect(toImmutableList());
    }
}


