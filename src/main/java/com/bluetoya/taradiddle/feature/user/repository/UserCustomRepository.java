package com.bluetoya.taradiddle.feature.user.repository;

import com.bluetoya.taradiddle.feature.user.dto.UserDto;
import com.mongodb.client.result.UpdateResult;

public interface UserCustomRepository {

    void updateLastLoginDate(String email);

    UpdateResult updateUser(String userId, UserDto user);

    UpdateResult updatePassword(String userId, String password);
}
