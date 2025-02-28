package com.bluetoya.taradiddle.feature.auth.dto;

public record SignInRequest(String userId, String password, String confirmPassword, String username, String firstName, String lastName) {

}
