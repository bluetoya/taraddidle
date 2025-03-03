package com.bluetoya.taradiddle.feature.user;

import com.bluetoya.taradiddle.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public ApiResponse<User> getUser(@PathVariable String username) {
        return new ApiResponse<>(userService.getUser(username));
    }

    @PutMapping
    public ApiResponse<User> update(@RequestBody UserDto user) {
        return new ApiResponse<>(userService.update(user));
    }

    @DeleteMapping("/{username}")
    public void delete(@PathVariable String username) {
        userService.delete(username);
    }
}
