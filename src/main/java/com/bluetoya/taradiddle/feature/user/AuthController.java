package com.bluetoya.taradiddle.feature.user;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return null;
    }

    @GetMapping("/sign-in")
    public String signIn(Model model) {
    model.addAttribute("authRequest", new AuthRequest());
        return null;
    }

}
