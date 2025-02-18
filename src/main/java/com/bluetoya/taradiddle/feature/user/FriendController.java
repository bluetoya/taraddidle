package com.bluetoya.taradiddle.feature.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {

    private final UserService userService;

    @PatchMapping("/{username}")
    public User addFriend(@PathVariable String username, @RequestBody FriendDto friend) {
        return userService.addFriend(username, friend);
    }

}
