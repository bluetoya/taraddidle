package com.bluetoya.taradiddle.feature.auth.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import com.bluetoya.taradiddle.feature.auth.entity.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;

@RequiredArgsConstructor
public class TokenCustomRepositoryImpl implements TokenCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public void upsertRefreshToken(Token token) {
        mongoTemplate.update(Token.class)
            .matching(query(where("userId").is(token.getUserId())))
            .apply(new Update()
                .set("refreshToken", token.getRefreshToken())
                .set("createdAt", token.getCreatedAt())
            )
            .upsert();
    }
}
