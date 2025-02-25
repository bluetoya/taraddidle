package com.bluetoya.taradiddle.feature.user;

public record UserDto(String username, String firstName, String lastName, String authUserId) {

    public UserDto(String username, String firstName, String lastName) {
        this(username, firstName, lastName, null);
    }

}
