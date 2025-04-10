package com.bluetoya.taradiddle.feature.auth.controller;

import com.bluetoya.taradiddle.common.ApiResponse;
import com.bluetoya.taradiddle.common.constant.CommonConstant;
import com.bluetoya.taradiddle.feature.auth.dto.AuthRequest;
import com.bluetoya.taradiddle.feature.auth.dto.AuthResponse;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.auth.dto.SignInResponse;
import com.bluetoya.taradiddle.feature.auth.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * 로그인
     * @param request 로그인 요청
     * @param response 로그인 여부
     * @return AuthResponse
     */
    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(final @RequestBody AuthRequest request,
        HttpServletResponse response) {
        return new ApiResponse<>(authenticationService.login(request, response));
    }

    /**
     * 회원 가입
     * @param request 회원가입 요청
     * @return SignInResponse
     */
    @PostMapping("/sign-in")
    public ApiResponse<SignInResponse> signIn(final @RequestBody SignInRequest request) {
        return new ApiResponse<>(authenticationService.signIn(request));
    }

    /**
     * 리프레시 토큰 재발급
     * @param request 리프레시 토큰 요청
     * @param refreshToken 리프레시 토큰
     * @param response http 응답
     * @return AuthResponse
     */
    @PostMapping("/refresh")
    public ApiResponse<AuthResponse> refresh(final @RequestBody AuthRequest request,
        @RequestHeader(CommonConstant.REFRESH_TOKEN_HEADER) String refreshToken,
        HttpServletResponse response) {
        return new ApiResponse<>(
            authenticationService.refresh(request, refreshToken, response));
    }

    /**
     * 로그아웃
     * @param userId 유저 아이디
     * @return AuthResponse
     */
    @DeleteMapping("/logout/{userId}")
    public ApiResponse<AuthResponse> logout(
        final @PathVariable String userId) {
        return new ApiResponse<>(authenticationService.logout(userId));
    }

}
