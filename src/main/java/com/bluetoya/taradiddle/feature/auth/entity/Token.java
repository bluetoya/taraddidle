package com.bluetoya.taradiddle.feature.auth.entity;

import com.bluetoya.taradiddle.common.util.DateUtil;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document("tokens")
public class Token {
    @Id
    private String id;
    private String userId;
    private String email;
    private String refreshToken;
    private LocalDateTime createdAt;

    public static Token of(String userId, String email, String refreshToken) {
        return Token.builder()
            .userId(userId)
            .email(email)
            .refreshToken(refreshToken)
            .createdAt(DateUtil.now())
            .build();
    }
}
