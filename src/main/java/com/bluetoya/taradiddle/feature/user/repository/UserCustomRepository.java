package com.bluetoya.taradiddle.feature.user.repository;

import com.bluetoya.taradiddle.feature.user.dto.UserDto;
import com.bluetoya.taradiddle.feature.user.enums.UserStatus;
import com.mongodb.client.result.UpdateResult;

public interface UserCustomRepository {

    UpdateResult updateLoginInfo(String userId, UserStatus status);

    UpdateResult updateUser(String userId, UserDto user);

    UpdateResult updatePassword(String userId, String password);
}
