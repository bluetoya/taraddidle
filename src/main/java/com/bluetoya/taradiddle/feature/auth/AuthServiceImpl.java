package com.bluetoya.taradiddle.feature.auth;

import com.bluetoya.taradiddle.common.config.JwtProvider;
import com.bluetoya.taradiddle.common.exception.CustomException;
import com.bluetoya.taradiddle.common.exception.errorcode.AuthErrorCode;
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

    @Override
    public LoginResponse login(String userId, String password) {
        AuthUser authUser = authRepository.findByUserId(userId);

        if (!isUserExists(authUser)) {
            throw new CustomException(AuthErrorCode.USER_ID_NOT_FOUND);
        }

        if (!bCryptPasswordEncoder.matches(password, authUser.getPassword())) {
            throw new CustomException(AuthErrorCode.WRONG_PASSWORD);
        }

        return new LoginResponse(jwtProvider.generateAccessToken(userId));
    }

    private boolean isUserExists(AuthUser authUser) {
        return Objects.nonNull(authUser);
    }

    @Override
    public SignInResponse signIn(SignInRequest request) {
        // TODO :: 입력한 아이디 유효성 검사
        // TODO :: 비밀번호 길이나 조합 내역 검사

        if (!isPasswordCorrect(request)) {
            throw new CustomException(AuthErrorCode.UNMATCHED_PASSWORD);
        }

        String encryptedPassword = bCryptPasswordEncoder.encode(request.password());

        AuthUser authUser = authRepository.save(AuthUser.of(request.userId(), encryptedPassword));
        // TODO :: authUser 생성 후 user도 생성해서 키로 매핑

        // TODO :: authUser와 user 내용도 같이 반환
        return new SignInResponse("ok");
    }

    private boolean isPasswordCorrect(SignInRequest request) {
        return request.password().equals(request.confirmPassword());
    }
}
