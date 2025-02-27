package com.bluetoya.taradiddle.feature.auth.dto;

import com.bluetoya.taradiddle.feature.auth.entity.Auth;
import com.bluetoya.taradiddle.feature.user.User;

public record SignInResponse(Auth auth, User user) {

}
