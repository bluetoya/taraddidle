package com.bluetoya.taradiddle.feature.auth.dto;

import com.bluetoya.taradiddle.feature.auth.entity.Auth;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthDto {

    private String userId;
    private String password;
    private LocalDateTime signInDate;

    public static AuthDto from(Auth auth) {
        return AuthDto.builder()
            .userId(auth.getUserId())
            .password(auth.getPassword())
            .signInDate(auth.getSignInDate())
            .build();
    }
}
