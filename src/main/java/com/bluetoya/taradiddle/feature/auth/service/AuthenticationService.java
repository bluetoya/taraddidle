package com.bluetoya.taradiddle.feature.auth.service;

import com.bluetoya.taradiddle.feature.auth.dto.AuthRequest;
import com.bluetoya.taradiddle.feature.auth.dto.AuthResponse;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.auth.dto.SignInResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {

    AuthResponse login(AuthRequest request, HttpServletResponse response);

    SignInResponse signIn(SignInRequest request);

    AuthResponse refresh(AuthRequest request, HttpServletResponse response);
}
