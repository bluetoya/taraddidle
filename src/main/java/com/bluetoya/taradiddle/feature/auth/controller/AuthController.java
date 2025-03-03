package com.bluetoya.taradiddle.feature.auth.controller;

import com.bluetoya.taradiddle.common.ApiResponse;
import com.bluetoya.taradiddle.feature.auth.dto.LoginRequest;
import com.bluetoya.taradiddle.feature.auth.service.AuthService;
import com.bluetoya.taradiddle.feature.auth.dto.LoginResponse;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.auth.dto.SignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public ApiResponse<LoginResponse> login(final @RequestBody LoginRequest request) {
        return new ApiResponse<>(authService.login(request));
    }

    @PostMapping("/sign-in")
    public ApiResponse<SignInResponse> signIn(final @RequestBody SignInRequest request) {
        return new ApiResponse<>(authService.signIn(request));
    }

}
