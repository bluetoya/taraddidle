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

  @Override
  public Auth getAuthUser(String userId) {
    return authRepository.findByUserId(userId);
  }

  // TODO :: exception 관리 클래스 생성 필요
  @Override
  public LoginResponse login(String userId, String password) {
    Auth authUser = authRepository.findByUserId(userId);

    if (!isUserExists(authUser)) {
      throw new RuntimeException();
    }

    if (!bCryptPasswordEncoder.matches(password, authUser.getPassword())) {
      throw new RuntimeException();
    }

    String accessToken = jwtProvider.generateAccessToken(userId);

    return new LoginResponse(accessToken);
  }

  private boolean isUserExists(Auth authUser) {
    return Objects.nonNull(authUser);
  }

  @Override
  public SignInResponse signIn(SignInRequest request) {
    if (!isPasswordCorrect(request)) {
      throw new RuntimeException();
    }

    // TODO :: 로그인 시 필요한 validation 추가
    authRepository.save(Auth.of(request));
    return new SignInResponse("ok");
  }

  private boolean isPasswordCorrect(SignInRequest request) {
      return request.password().equals(request.confirmPassword());
  }
}
