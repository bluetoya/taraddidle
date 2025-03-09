package com.bluetoya.taradiddle.feature.user;

public interface UserCustomRepository {

    void updateLastLoginDate(String email);
}
