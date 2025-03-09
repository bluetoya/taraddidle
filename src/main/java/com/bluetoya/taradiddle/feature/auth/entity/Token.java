package com.bluetoya.taradiddle.feature.auth.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document("token")
public class Token {
    @Id
    private String id;
    private String email;
    private String refreshToken;
    private String createdAt;

    public static Token create(String email, String refreshToken) {
        return Token.builder()
            .email(email)
            .refreshToken(refreshToken)
            .build();
    }
}
