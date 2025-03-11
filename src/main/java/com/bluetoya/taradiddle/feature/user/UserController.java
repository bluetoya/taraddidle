package com.bluetoya.taradiddle.feature.user;

import com.bluetoya.taradiddle.common.ApiResponse;
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

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable String userId) {
        return new ApiResponse<>(userService.getUser(userId));
    }

    @PutMapping("/{userId}")
    public ApiResponse<String> update(@PathVariable String userId, @RequestBody UserDto user) {
        return new ApiResponse<>(userService.update(userId, user));
    }

    @PatchMapping("{/userId}")
    public ApiResponse<String> updatePassword(@PathVariable String userId, @RequestBody PasswordChangeRequest passwordChangeRequest) {
        return new ApiResponse<>(userService.updatePassword(userId, passwordChangeRequest));
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable String userId) {
        userService.delete(userId);
    }
}
