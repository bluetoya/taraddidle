package com.bluetoya.taradiddle.feature.auth.controller;

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
    public LoginResponse login(final @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/sign-in")
    public SignInResponse signIn(final @RequestBody SignInRequest request) {
        return authService.signIn(request);
    }

}
