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

    @GetMapping
    public ApiResponse<List<String>> getBlockedUsers(@AuthenticationPrincipal String userId) {
        log.info("Get blocked users for {}", userId);
        return new ApiResponse<>(userBlockService.getBlockedUsers(userId));
    }

    @PostMapping("/{blockUserId}")
    public ApiResponse<String> blockUser(@AuthenticationPrincipal String userId, @PathVariable String blockUserId) {
        return new ApiResponse<>(userBlockService.addBlockUser(userId, blockUserId));
    }

    @DeleteMapping("/{blockUserId}")
    public ApiResponse<String> unblockUser(@AuthenticationPrincipal String userId, @PathVariable String blockUserId) {
        return new ApiResponse<>(userBlockService.unblockUser(userId, blockUserId));
    }

}
