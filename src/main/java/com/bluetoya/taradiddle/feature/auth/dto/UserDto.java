package com.bluetoya.taradiddle.feature.auth.dto;

import com.bluetoya.taradiddle.feature.user.User;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {

    private String username;
    private String firstName;
    private String lastName;
    private List<String> friends;
    private LocalDateTime createdAt;
    private String authId;

    public static UserDto from(User user) {
        return UserDto.builder()
            .username(user.getUsername())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .friends(user.getFriends())
            .createdAt(user.getCreatedAt())
            .authId(user.getAuthId())
            .build();
    }

}
