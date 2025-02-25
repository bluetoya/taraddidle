package com.bluetoya.taradiddle.feature.auth;

import com.bluetoya.taradiddle.feature.user.User;

public record SignInResponse(AuthUser authUser, User user) {

}
