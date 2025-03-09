package com.bluetoya.taradiddle.feature.user;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {

    @NotNull
    private String username;
    String firstName;
    private String lastName;

}
