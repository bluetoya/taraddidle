package com.bluetoya.taradiddle.feature.auth.controller;

import com.bluetoya.taradiddle.common.ApiResponse;
import com.bluetoya.taradiddle.common.constant.CommonConstant;
import com.bluetoya.taradiddle.feature.auth.dto.AuthRequest;
import com.bluetoya.taradiddle.feature.auth.dto.AuthResponse;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.auth.dto.SignInResponse;
import com.bluetoya.taradiddle.feature.auth.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(final @RequestBody AuthRequest request,
        HttpServletResponse response) {
        return new ApiResponse<>(authenticationService.login(request, response));
    }

    @PostMapping("/sign-in")
    public ApiResponse<SignInResponse> signIn(final @RequestBody SignInRequest request) {
        return new ApiResponse<>(authenticationService.signIn(request));
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthResponse> refresh(final @RequestBody AuthRequest authRequest,
        @RequestHeader(CommonConstant.REFRESH_TOKEN_HEADER) String refreshToken,
        HttpServletResponse response) {
        return new ApiResponse<>(
            authenticationService.refresh(authRequest, refreshToken, response));
    }

    @DeleteMapping("/logout")
    public ApiResponse<AuthResponse> logout(
        @RequestHeader(CommonConstant.X_USER_ID) String userId) {
        return new ApiResponse<>(authenticationService.logout(userId));
    }

}
