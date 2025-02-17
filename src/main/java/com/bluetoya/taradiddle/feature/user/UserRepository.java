package com.bluetoya.taradiddle.feature.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);

    void deleteByUsername(String username);
}
