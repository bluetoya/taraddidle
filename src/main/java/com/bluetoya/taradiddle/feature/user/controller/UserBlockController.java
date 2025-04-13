package com.bluetoya.taradiddle.feature.user.controller;

import com.bluetoya.taradiddle.common.ApiResponse;
import com.bluetoya.taradiddle.feature.user.service.UserBlockService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/block")
@RequiredArgsConstructor
@Slf4j
public class UserBlockController {

    private final UserBlockService userBlockService;

    /**
     * 차단한 유저 리스트 조회
     * @param userId 유저 이메일
     * @return List<String> 차단된 유저 아이디 리스트
     */
    @GetMapping
    public ApiResponse<List<String>> getBlockedUsers(@AuthenticationPrincipal String userId) {
        log.info("Get blocked users for {}", userId);
        return new ApiResponse<>(userBlockService.getBlockedUsers(userId));
    }

    /**
     * 유저 차단
     * @param userId 유저 아이디
     * @param blockUserId 차단할 유저 아이디
     * @return String 차단된 유저 아이디
     */
    @PostMapping("/{blockUserId}")
    public ApiResponse<String> blockUser(@AuthenticationPrincipal String userId, @PathVariable String blockUserId) {
        return new ApiResponse<>(userBlockService.addBlockUser(userId, blockUserId));
    }

    /**
     * 유저 차단 해제
     * @param userId 유저 아이디
     * @param blockUserId 차단했던 유저 아이디
     * @return String 차단 해제한 유저 아이디
     */
    @DeleteMapping("/{blockUserId}")
    public ApiResponse<String> unblockUser(@AuthenticationPrincipal String userId, @PathVariable String blockUserId) {
        return new ApiResponse<>(userBlockService.unblockUser(userId, blockUserId));
    }

}
