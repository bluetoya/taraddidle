package com.bluetoya.taradiddle.feature.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public LoginResponse login(@RequestParam String userId, @RequestParam String password) {
        return authService.login(userId, password);
    }

    @PostMapping("/sign-in")
    public SignInResponse signIn(final @RequestBody SignInRequest request) {
        return authService.signIn(request);
    }

}
