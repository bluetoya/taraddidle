package com.bluetoya.taradiddle.feature.user.controller;

import com.bluetoya.taradiddle.common.ApiResponse;
import com.bluetoya.taradiddle.feature.user.dto.PasswordChangeRequest;
import com.bluetoya.taradiddle.feature.user.dto.UserDto;
import com.bluetoya.taradiddle.feature.user.dto.UserResponse;
import com.bluetoya.taradiddle.feature.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 유저 정보 조회
     * @param userId 유저 아이디
     * @return UserResponse
     */
    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable String userId) {
        return new ApiResponse<>(userService.getUser(userId));
    }

    /**
     * 유저 정보 업데이트
     * @param userId 유저 아이디
     * @param user 유저 정보
     * @return String
     */
    @PutMapping("/{userId}")
    public ApiResponse<String> update(@PathVariable String userId, @RequestBody UserDto user) {
        return new ApiResponse<>(userService.update(userId, user));
    }

    /**
     * 유저 패스워드 업데이트
     * @param userId 유저 아이디
     * @param passwordChangeRequest 패스워드 변경 요청
     * @return String
     */
    @PatchMapping("/{userId}")
    public ApiResponse<String> updatePassword(@PathVariable String userId, @RequestBody PasswordChangeRequest passwordChangeRequest) {
        return new ApiResponse<>(userService.updatePassword(userId, passwordChangeRequest));
    }

    /**
     * 유저 정보 삭제
     * @param userId 유저 아이디
     */
    @DeleteMapping("/{userId}")
    public void delete(@PathVariable String userId) {
        userService.delete(userId);
    }
}
