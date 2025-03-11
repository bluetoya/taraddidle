package com.bluetoya.taradiddle.feature.user;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

import com.bluetoya.taradiddle.common.util.DateUtil;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

    @Override
    public UpdateResult updateUser(String userId, UserDto user) {
        Query query = new Query(Criteria.where("_id").is(userId));

        Update update = new Update()
            .set("firstName", user.getFirstName())
            .set("lastName", user.getLastName())
            .set("updatedAt", DateUtil.now());

        return mongoTemplate.updateFirst(query, update, User.class);
    }

    @Override
    public UpdateResult updatePassword(String userId, String password) {
        Query query = new Query(Criteria.where("_id").is(userId));

        Update update = new Update()
            .set("passwordHash", password);

        return mongoTemplate.updateFirst(query, update, User.class);
    }
}
