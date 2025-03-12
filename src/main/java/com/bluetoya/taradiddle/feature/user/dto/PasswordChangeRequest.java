package com.bluetoya.taradiddle.feature.user.dto;

public record PasswordChangeRequest(String oldPassword, String newPassword) {
}
