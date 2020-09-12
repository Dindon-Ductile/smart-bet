package com.smartbet.demo.bet.repository;

import com.google.common.collect.ImmutableList;
import com.smartbet.demo.bet.domain.UserCombinedBet;
import com.smartbet.demo.bet.domain.UserCombinedBetCreation;
import com.smartbet.demo.bet.domain.UserCombinedBetUpdate;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class UserCombinedBetsRepository {
    private final MongoUserCombinedBetsRepository mongoUserCombinedBetsRepository;
    private final MongoOperations mongoOperations;

    public UserCombinedBetsRepository(MongoUserCombinedBetsRepository mongoUserCombinedBetsRepository,
                                      MongoOperations mongoOperations) {
        this.mongoUserCombinedBetsRepository = mongoUserCombinedBetsRepository;
        this.mongoOperations = mongoOperations;
    }

    public void create(UserCombinedBetCreation creation) {
        MongoUserCombinedBet mongo = MongoUserCombinedBet.build(creation.toDomain());
        mongoUserCombinedBetsRepository.insert(mongo);
    }

    public Optional<UserCombinedBet> findById(UUID id) {
        return mongoUserCombinedBetsRepository.findById(id)
                .map(MongoUserCombinedBet::toDomain);
    }

    public ImmutableList<UserCombinedBet> findAllByUserId(UUID userId) {
        return mongoUserCombinedBetsRepository.findAllByUserId(userId).stream()
                .map(MongoUserCombinedBet::toDomain)
                .collect(toImmutableList());
    }

    public void update(UserCombinedBetUpdate update) {
        Query byId = query(where("_id").is(update.getId()));
        Update updateFields = new Update()
                .set("entries", update.getEntries())
                .set("betMoney", update.getBetMoney())
                .set("lastUpdatedAt", update.getLastUpdatedAt());
        mongoOperations.updateFirst(byId, updateFields, MongoUserCombinedBet.class);
    }

    public void delete(UUID id) {
        mongoUserCombinedBetsRepository.deleteById(id);
    }

}
