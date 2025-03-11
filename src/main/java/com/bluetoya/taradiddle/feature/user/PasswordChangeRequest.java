package com.bluetoya.taradiddle.feature.user;

public record PasswordChangeRequest(String oldPassword, String newPassword) {
}
