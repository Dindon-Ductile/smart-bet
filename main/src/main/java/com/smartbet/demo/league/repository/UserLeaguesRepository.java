package com.smartbet.demo.league.repository;

import com.google.common.collect.ImmutableSet;
import com.smartbet.demo.league.domain.UserLeague;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.google.common.collect.ImmutableSet.toImmutableSet;

@Service
public class UserLeaguesRepository {
    private final MongoUserLeaguesRepository mongoUserLeaguesRepository;

    public UserLeaguesRepository(MongoUserLeaguesRepository mongoUserLeaguesRepository) {
        this.mongoUserLeaguesRepository = mongoUserLeaguesRepository;
    }

    public ImmutableSet<UserLeague> getUserLeagues(UUID userId) {
        return mongoUserLeaguesRepository.findAll().stream()
                .map(MongoUserLeague::toUserLeague)
                .collect(toImmutableSet());
    }

    public void createUserLeague(UserLeague league) {
        mongoUserLeaguesRepository.insert(MongoUserLeague.build(league));
    }

    public void deleteUserLeague(UUID userId, int leagueId) {
        MongoUserLeagueKey key = new MongoUserLeagueKey(userId, leagueId);
        mongoUserLeaguesRepository.deleteById(key);
    }
}
