package com.bluetoya.taradiddle.feature.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserService {

    private final AuthRepository authRepository;

    public AuthUser getAuthUser(String userId) {
        return authRepository.findByUserId(userId);
    }
}
