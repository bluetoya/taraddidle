package com.bluetoya.taradiddle.feature.auth.repository;

public interface AuthCustomRepository {

    void saveRefreshToken(String userId, String refreshToken);
}
