package com.bluetoya.taradiddle.feature.auth.service;

import com.bluetoya.taradiddle.feature.auth.dto.LoginRequest;
import com.bluetoya.taradiddle.feature.auth.dto.LoginResponse;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.auth.dto.SignInResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest request, HttpServletResponse response);

    SignInResponse signIn(SignInRequest request);

}
