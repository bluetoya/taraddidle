package com.bluetoya.taradiddle.feature.auth;

public record SignInRequest(String userId, String password, String confirmPassword) {}
