package com.bluetoya.taradiddle.feature.auth;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document(collection = "auths")
public class Auth {

    @Id
    private String id;
    private String userId;
    private String password;

    public static Auth of(SignInRequest request) {
        return Auth.builder().userId(request.userId()).password(request.password()).build();
    }
}
