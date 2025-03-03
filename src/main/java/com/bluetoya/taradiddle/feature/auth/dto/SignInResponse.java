package com.bluetoya.taradiddle.feature.auth.dto;

import com.bluetoya.taradiddle.feature.user.UserDto;

public record SignInResponse(AuthDto auth, UserDto user) {

}
