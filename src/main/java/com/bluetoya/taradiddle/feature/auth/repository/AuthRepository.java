package com.bluetoya.taradiddle.feature.auth.repository;

import com.bluetoya.taradiddle.feature.auth.entity.Auth;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthRepository extends MongoRepository<Auth, String> {

    Auth findByUserId(String userId);
}
