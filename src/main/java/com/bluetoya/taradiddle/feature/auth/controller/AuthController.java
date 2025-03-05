package com.bluetoya.taradiddle.feature.auth.controller;

import com.bluetoya.taradiddle.feature.auth.dto.LoginRequest;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.auth.dto.SignInResponse;
import com.bluetoya.taradiddle.feature.auth.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @GetMapping("/login")
    public void login(final @RequestBody LoginRequest request, HttpServletResponse response) {
        authenticationService.login(request, response);
    }

    @PostMapping("/sign-in")
    public SignInResponse signIn(final @RequestBody SignInRequest request) {
        return authenticationService.signIn(request);
    }

}
