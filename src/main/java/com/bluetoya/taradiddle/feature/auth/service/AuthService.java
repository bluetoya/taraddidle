package com.bluetoya.taradiddle.feature.auth.service;

import com.bluetoya.taradiddle.feature.auth.dto.LoginRequest;
import com.bluetoya.taradiddle.feature.auth.dto.LoginResponse;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.auth.dto.SignInResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    SignInResponse signIn(SignInRequest request);
}
