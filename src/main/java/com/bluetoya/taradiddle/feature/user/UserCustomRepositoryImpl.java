package com.bluetoya.taradiddle.feature.user;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

import com.bluetoya.taradiddle.common.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public void updateLastLoginDate(String userId) {
        mongoTemplate.updateFirst(query(where("_id").is(userId)),
            update("lastLoginDate", DateUtil.now()), User.class);
    }
}
