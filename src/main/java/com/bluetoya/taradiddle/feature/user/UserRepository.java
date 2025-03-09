package com.bluetoya.taradiddle.feature.user;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>, UserCustomRepository {

    void deleteByUsername(String username);

    Optional<User> findByEmail(String email);
}
