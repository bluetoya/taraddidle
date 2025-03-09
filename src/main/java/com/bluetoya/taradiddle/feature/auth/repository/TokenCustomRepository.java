package com.bluetoya.taradiddle.feature.auth.repository;

import com.bluetoya.taradiddle.feature.auth.entity.Token;

public interface TokenCustomRepository {

    void upsertRefreshToken(Token token);

}
