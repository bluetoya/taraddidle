package com.bluetoya.taradiddle.feature.user.controller;

import com.bluetoya.taradiddle.common.ApiResponse;
import com.bluetoya.taradiddle.feature.user.dto.FriendDto;
import com.bluetoya.taradiddle.feature.user.entity.User;
import com.bluetoya.taradiddle.feature.user.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @PatchMapping("/{userId}")
    public ApiResponse<User> addFriend(@PathVariable String userId, @RequestBody FriendDto friend) {
        return new ApiResponse<>(null);
    }

    @DeleteMapping("/{userId}")
    public ApiResponse<User> remove(@PathVariable String userId, @RequestBody FriendDto friend) {
        return new ApiResponse<>(null);
    }

}
