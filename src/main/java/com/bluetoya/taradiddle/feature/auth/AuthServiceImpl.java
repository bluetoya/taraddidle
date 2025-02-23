package com.bluetoya.taradiddle.feature.auth;

import com.bluetoya.taradiddle.common.config.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtProvider jwtProvider;

    private final AuthRepository authRepository;

    // TODO :: exception 관리 클래스 생성 필요
    @Override
    public LoginResponse login(String userId, String password) {
        AuthUser authUser = authRepository.findByUserId(userId);

        if (!isUserExists(authUser)) {
            throw new RuntimeException("존재하지 않는 회원입니다.");
        }

        if (!bCryptPasswordEncoder.matches(password, authUser.getPassword())) {
            throw new RuntimeException("틀린 패스워드를 입력했습니다.");
        }

        return new LoginResponse(jwtProvider.generateAccessToken(userId));
    }

    private boolean isUserExists(AuthUser authUser) {
        return Objects.nonNull(authUser);
    }

    @Override
    public SignInResponse signIn(SignInRequest request) {
        if (!isPasswordCorrect(request)) {
            throw new RuntimeException("입력하신 패스워드와 확인용 패스워드가 다릅니다.");
        }

        String encryptedPassword = bCryptPasswordEncoder.encode(request.password());

        // TODO :: 로그인 시 필요한 validation 추가
        AuthUser authUser = authRepository.save(AuthUser.of(request.userId(), encryptedPassword));
        return new SignInResponse("ok");
    }

    private boolean isPasswordCorrect(SignInRequest request) {
        return request.password().equals(request.confirmPassword());
    }
}
