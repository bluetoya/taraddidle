package com.bluetoya.taradiddle.feature.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class UserDto {
    @NonNull private final String username;
    private final String firstName;
    private final String lastName;
}
