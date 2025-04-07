package com.bluetoya.taradiddle.feature.user.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import com.bluetoya.taradiddle.common.util.DateUtil;
import com.bluetoya.taradiddle.feature.user.entity.User;
import com.bluetoya.taradiddle.feature.user.dto.UserDto;
import com.bluetoya.taradiddle.feature.user.enums.UserStatus;
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
    public UpdateResult updateLoginInfo(String userId, UserStatus status) {
        Query query = query(where("_id").is(userId));

        Update update = new Update()
            .set("status", status);

        if (status == UserStatus.ONLINE) {
            update.set("lastLoginDate", DateUtil.now());
        }

        return mongoTemplate.updateFirst(query, update, User.class);
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
