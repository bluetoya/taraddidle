package com.bluetoya.taradiddle.feature.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userService.getUser(username);
    }

    @PostMapping
    public User create(@RequestBody UserDto user) {
        return userService.create(user);
    }

    @PutMapping
    public User update(@RequestBody UserDto user) {
        return userService.update(user);
    }

    @DeleteMapping("/{username}")
    public void delete(@PathVariable String username) {
        userService.delete(username);
    }

    @PatchMapping("/{username}")
    public User addFriend(@PathVariable String username, @RequestBody FriendDto friend) {
        return userService.addFriend(username, friend);
    }
}
