package com.bluetoya.taradiddle.feature.auth.dto;

import com.bluetoya.taradiddle.feature.auth.entity.AuthUser;
import com.bluetoya.taradiddle.feature.user.User;

public record SignInResponse(AuthUser authUser, User user) {

}
