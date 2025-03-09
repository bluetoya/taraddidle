package com.bluetoya.taradiddle.feature.auth.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthDto {

    private String passwordHash;
    private LocalDateTime firstSignInDate;
    private LocalDateTime lastLoginDate;

}
