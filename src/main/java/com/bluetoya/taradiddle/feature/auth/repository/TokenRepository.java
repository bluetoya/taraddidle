package com.bluetoya.taradiddle.feature.auth.repository;

import com.bluetoya.taradiddle.feature.auth.entity.Token;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepository extends MongoRepository<Token, String>, TokenCustomRepository {

    Optional<Token> findByEmail(String email);

    void deleteByUserId(String userId);
}
