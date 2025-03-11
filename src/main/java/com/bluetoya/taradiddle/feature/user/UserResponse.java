package com.bluetoya.taradiddle.feature.user;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

    private String userId;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDateTime updatedAt;

    public static UserResponse from(User user) {
        return UserResponse.builder()
            .userId(user.getId())
            .email(user.getEmail())
            .username(user.getUsername())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .updatedAt(user.getUpdatedAt())
            .build();
    }
}
