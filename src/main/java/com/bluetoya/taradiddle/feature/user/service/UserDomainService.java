package com.bluetoya.taradiddle.feature.user.service;

import com.bluetoya.taradiddle.common.exception.CustomException;
import com.bluetoya.taradiddle.common.exception.errorcode.AuthErrorCode;
import com.bluetoya.taradiddle.common.exception.errorcode.UserErrorCode;
import com.bluetoya.taradiddle.feature.auth.entity.Token;
import com.bluetoya.taradiddle.feature.auth.repository.TokenRepository;
import com.bluetoya.taradiddle.feature.user.entity.User;
import com.bluetoya.taradiddle.feature.user.dto.UserDto;
import com.bluetoya.taradiddle.feature.user.enums.UserStatus;
import com.bluetoya.taradiddle.feature.user.repository.UserRepository;
import com.mongodb.client.result.UpdateResult;
import java.util.List;
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

    public UpdateResult updateLoginInfo(String userId, UserStatus status) {
        return userRepository.updateLoginInfo(userId, status);
    }

    public void logout(String userId) {
        updateLoginInfo(userId, UserStatus.OFFLINE);
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

    public UpdateResult updatePassword(String userId, String password) {
        return userRepository.updatePassword(userId, password);
    }

    public List<String> getBlockedUsers(String userId) {
        return userRepository.findBlockedUsers(userId);
    }

    public UpdateResult blockUser(String userId, String blockUserId) {
        return userRepository.blockUser(userId, blockUserId);
    }

    public UpdateResult unblockUser(String userId, String blockUserId) {
        return userRepository.unblockUser(userId, blockUserId);
    }
}
