package com.smartbet.demo.bet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface MongoUserCombinedBetsRepository extends MongoRepository<MongoUserCombinedBet, UUID> {
    List<MongoUserCombinedBet> findAllByUserId(UUID userId);
}
