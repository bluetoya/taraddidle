package com.bluetoya.taradiddle.feature.user.service;

import com.bluetoya.taradiddle.common.exception.CustomException;
import com.bluetoya.taradiddle.common.exception.errorcode.UserErrorCode;
import com.bluetoya.taradiddle.feature.auth.validator.BCryptValidator;
import com.bluetoya.taradiddle.feature.user.dto.PasswordChangeRequest;
import com.bluetoya.taradiddle.feature.user.entity.User;
import com.bluetoya.taradiddle.feature.user.dto.UserDto;
import com.bluetoya.taradiddle.feature.user.dto.UserResponse;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDomainService userDomainService;
    private final BCryptValidator bCryptValidator;

    public UserResponse getUser(String userId) {
        return UserResponse.from(userDomainService.findById(userId));
    }

    public String update(String userId, UserDto user) {
        UpdateResult result = userDomainService.updateUser(userId, user);
        if (result.getModifiedCount() > 0) {
            return "유저 업데이트에 성공했습니다.";
        }

        throw new CustomException(UserErrorCode.USER_UPDATE_FAILED);
    }

    public String updatePassword(String userId, PasswordChangeRequest passwordChangeRequest) {
        User user = userDomainService.findById(userId);
        bCryptValidator.validatePassword(passwordChangeRequest.oldPassword(),
            user.getAuth().getPasswordHash());
        UpdateResult result = userDomainService.updatePassword(userId,
            bCryptValidator.encode(passwordChangeRequest.newPassword()));

        if (result.getModifiedCount() > 0) {
            return "패스워드 업데이트에 성공했습니다.";
        }

        throw new CustomException(UserErrorCode.PASSWORD_UPDATE_FAILED);
    }

    public void delete(String userId) {
        userDomainService.deleteById(userId);
    }

}
