package com.bluetoya.taradiddle.feature.auth;

public interface AuthService {

    LoginResponse login(String userId, String password);

    SignInResponse signIn(SignInRequest request);
}
