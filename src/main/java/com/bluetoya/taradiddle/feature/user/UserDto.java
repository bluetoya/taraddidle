package com.bluetoya.taradiddle.feature.user;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {

    @NotNull
    private String username;
    String firstName;
    private String lastName;
    private List<String> friends;
    private LocalDateTime createdAt;
    private String authId;

}
