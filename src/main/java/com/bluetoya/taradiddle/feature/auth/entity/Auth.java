package com.bluetoya.taradiddle.feature.auth.entity;

import com.bluetoya.taradiddle.common.util.DateUtil;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Auth {

    private String passwordHash;
    private LocalDateTime firstSignInDate;
    private LocalDateTime lastLoginDate;

    public static Auth of(String password) {
        return Auth.builder().passwordHash(password).firstSignInDate(DateUtil.now())
            .lastLoginDate(DateUtil.now()).build();
    }
}
