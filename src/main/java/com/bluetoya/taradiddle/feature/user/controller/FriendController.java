package com.bluetoya.taradiddle.feature.user.controller;

import com.bluetoya.taradiddle.feature.user.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

//    @PatchMapping("/{username}")
//    public ApiResponse<User> addFriend(@PathVariable String username, @RequestBody FriendDto friend) {
//        return new ApiResponse<>(friendService.addFriend(username, friend));
//    }
//
//    @DeleteMapping("/{username}")
//    public ApiResponse<User> remove(@PathVariable String username, @RequestBody FriendDto friend) {
//        return new ApiResponse<>(friendService.removeFriend(username, friend));
//    }

}
