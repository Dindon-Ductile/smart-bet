package com.smartbet.demo.league.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUserLeaguesRepository extends MongoRepository<MongoUserLeague, MongoUserLeagueKey> {
}
