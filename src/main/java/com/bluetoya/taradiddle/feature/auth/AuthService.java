package com.bluetoya.taradiddle.feature.auth;

public interface AuthService {

    Auth getAuthUser(String userId);

    LoginResponse login(String userId, String password);

    SignInResponse signIn(SignInRequest request);
}
