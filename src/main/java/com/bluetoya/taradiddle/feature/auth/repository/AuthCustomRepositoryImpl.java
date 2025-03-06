package com.bluetoya.taradiddle.feature.auth.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

import com.bluetoya.taradiddle.feature.auth.entity.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthCustomRepositoryImpl implements AuthCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public void saveRefreshToken(String userId, String refreshToken) {
        mongoTemplate.updateFirst(query(where("userId").is(userId)),
            update("refreshToken", refreshToken), Auth.class);
    }
}
