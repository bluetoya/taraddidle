package com.bluetoya.taradiddle.feature.auth;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthRepository extends MongoRepository<Auth, String> {

    Auth findByUserId(String userId);
}
