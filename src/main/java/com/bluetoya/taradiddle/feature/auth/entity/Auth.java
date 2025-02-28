package com.bluetoya.taradiddle.feature.auth.entity;

import com.bluetoya.taradiddle.common.util.DateUtil;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document(collection = "auth")
public class Auth {

    @Id
    private String id;
    private String userId;
    private String password;
    private LocalDateTime signInDate;

    public static Auth of(String userId, String password) {
        return Auth.builder().userId(userId).password(password).signInDate(DateUtil.now())
            .build();
    }
}
