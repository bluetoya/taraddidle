package com.bluetoya.taradiddle.feature.auth;

import com.bluetoya.taradiddle.common.util.DateUtil;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document(collection = "authUsers")
public class AuthUser {

    @Id
    private String id;
    private String userId;
    private String password;
    private LocalDateTime signInTime;

    public static AuthUser of(String userId, String password) {
        return AuthUser.builder().userId(userId).password(password).signInTime(DateUtil.now())
            .build();
    }
}
