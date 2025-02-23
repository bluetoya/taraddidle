package com.bluetoya.taradiddle.feature.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestParam String userId, @RequestParam String password) {
        return authService.login(userId, password);
    }

    @GetMapping("/sign-in")
    public String signIn(LoginRequest loginRequest) {
        return null;
    }

}
