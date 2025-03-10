package com.bluetoya.taradiddle.feature.user;

import com.mongodb.client.result.UpdateResult;

public interface UserCustomRepository {

    void updateLastLoginDate(String email);

    UpdateResult updateUser(String userId, UserDto user);
}
