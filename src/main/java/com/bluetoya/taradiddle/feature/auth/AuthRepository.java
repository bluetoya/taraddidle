package com.bluetoya.taradiddle.feature.auth;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthRepository extends MongoRepository<AuthUser, String> {

    AuthUser findByUserId(String userId);
}
