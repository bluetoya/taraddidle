package com.bluetoya.taradiddle.feature.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 정적 페이지 리다이렉션 thymeleaf 사용으로 임시로 추가
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

}
