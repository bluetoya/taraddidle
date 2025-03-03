package com.bluetoya.taradiddle.feature.auth.dto;

import com.bluetoya.taradiddle.feature.user.User;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

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
