package com.bluetoya.taradiddle.feature.auth.validator;

import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignInValidator {

    private final UserDomainService userDomainService;

    public void validateSignIn(SignInRequest request) {
        ValidatorChain<SignInRequest> chain = new ValidatorChain<>();
        chain.addValidator(new UserIdValidator(userDomainService));
        chain.addValidator(new PasswordValidator());

        chain.validate(request);
    }

}
