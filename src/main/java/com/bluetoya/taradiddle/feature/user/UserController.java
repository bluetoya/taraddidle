package com.bluetoya.taradiddle.feature.user;

import com.bluetoya.taradiddle.common.ApiResponse;
import com.bluetoya.taradiddle.common.constant.CommonConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse<User> getUser(@RequestHeader(CommonConstant.X_USER_ID) String userId) {
        return new ApiResponse<>(userService.getUser(userId));
    }

    @PutMapping
    public ApiResponse<String> update(@RequestHeader(CommonConstant.X_USER_ID) String userId, @RequestBody UserDto user) {
        return new ApiResponse<>(userService.update(userId, user));
    }

    @DeleteMapping
    public void delete(@RequestHeader(CommonConstant.X_USER_ID) String userId) {
        userService.delete(userId);
    }
}
