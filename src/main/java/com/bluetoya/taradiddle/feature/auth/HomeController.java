package com.bluetoya.taradiddle.feature.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 정적 페이지 리다이렉션
 * thymeleaf 사용으로 임시로 추가
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/sign-in")
    public String signIn(Model model) {
        model.addAttribute("authRequest", new AuthRequest());
        return "signIn";
    }

    @GetMapping("/logout")
    public String logout() {
        return "home";
    }
}
