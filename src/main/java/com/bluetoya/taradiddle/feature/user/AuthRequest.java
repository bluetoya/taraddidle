package com.bluetoya.taradiddle.feature.user;

public record AuthRequest(String username, String password, String passwordConfirmation) {

    public AuthRequest() {
        this(null, null, null);
    }
}
