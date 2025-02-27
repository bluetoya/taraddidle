package com.bluetoya.taradiddle.feature.auth.repository;

import com.bluetoya.taradiddle.feature.auth.entity.AuthUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthRepository extends MongoRepository<AuthUser, String> {

    AuthUser findByUserId(String userId);
}
