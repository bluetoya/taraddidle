package com.bluetoya.taradiddle.feature.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @PatchMapping("/{username}")
    public User addFriend(@PathVariable String username, @RequestBody FriendDto friend) {
        return friendService.addFriend(username, friend);
    }

    @DeleteMapping("/{username}")
    public User remove(@PathVariable String username, @RequestBody FriendDto friend) {
        return friendService.removeFriend(username, friend);
    }

}
