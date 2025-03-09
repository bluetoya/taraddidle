package com.bluetoya.taradiddle.feature.user;

import com.bluetoya.taradiddle.common.exception.CustomException;
import com.bluetoya.taradiddle.common.exception.errorcode.AuthErrorCode;
import com.bluetoya.taradiddle.common.exception.errorcode.UserErrorCode;
import com.bluetoya.taradiddle.feature.auth.entity.Token;
import com.bluetoya.taradiddle.feature.auth.repository.TokenRepository;
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

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Token findTokenByEmail(String email) {
        return tokenRepository.findByEmail(email).orElseThrow(() -> new CustomException(
            AuthErrorCode.EMAIL_NOT_FOUND));
    }

    public void saveRefreshToken(Token token) {
        // TODO :: upsert required
        tokenRepository.save(token);
    }

    public void updateLastLoginDate(String email) {
        userRepository.updateLastLoginDate(email);
    }
}
