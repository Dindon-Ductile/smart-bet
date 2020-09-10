package com.smartbet.demo.league.repository;

import com.smartbet.demo.league.domain.UserLeague;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "userLeagues")
class MongoUserLeague {
    @Id
    private final MongoUserLeagueKey key;

    static MongoUserLeague build(UserLeague league) {
        return new MongoUserLeague(new MongoUserLeagueKey(league.getUserId(), league.getLeagueId()));
    }

    UserLeague toUserLeague() {
        return new UserLeague(key.getUserId(), key.getLeagueId());
    }
}
