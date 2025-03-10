package com.bluetoya.taradiddle.feature.user;

import com.bluetoya.taradiddle.common.exception.CustomException;
import com.bluetoya.taradiddle.common.exception.errorcode.AuthErrorCode;
import com.bluetoya.taradiddle.common.exception.errorcode.UserErrorCode;
import com.bluetoya.taradiddle.feature.auth.entity.Token;
import com.bluetoya.taradiddle.feature.auth.repository.TokenRepository;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDomainService {

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new CustomException(
            UserErrorCode.USER_NOT_FOUND));
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Token findTokenByEmail(String email) {
        return tokenRepository.findByEmail(email).orElseThrow(() -> new CustomException(
            AuthErrorCode.EMAIL_NOT_FOUND));
    }

    public void updateRefreshToken(Token token) {
        tokenRepository.upsertRefreshToken(token);
    }

    public void updateLastLoginDate(String userId) {
        userRepository.updateLastLoginDate(userId);
    }

    public void logout(String userId) {
        tokenRepository.deleteByUserId(userId);
    }

    public User findById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));
    }

    public void deleteById(String userId) {
        userRepository.deleteById(userId);
    }

    public UpdateResult updateUser(String userId, UserDto user) {
        return userRepository.updateUser(userId, user);
    }
}
