package com.bluetoya.taradiddle.feature.auth.validator;

import static com.bluetoya.taradiddle.common.util.RegexUtil.isEmailFormat;

import com.bluetoya.taradiddle.common.exception.CustomException;
import com.bluetoya.taradiddle.common.exception.errorcode.AuthErrorCode;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.user.UserDao;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserIdValidator implements Validator<SignInRequest> {

    private final UserDao userDao;

    @Override
    public void validate(SignInRequest request) {
        if (alreadyExists(request.email())) {
            throw new CustomException(AuthErrorCode.USER_ID_ALREADY_EXISTS);
        }

        if (!isEmailFormat(request.email())) {
            throw new CustomException(AuthErrorCode.INVALID_EMAIL_FORMAT);
        }
    }

    private boolean alreadyExists(String userId) {
        return Objects.nonNull(userDao.findByEmail(userId));
    }
}
