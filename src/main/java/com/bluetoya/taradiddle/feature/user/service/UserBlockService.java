package com.bluetoya.taradiddle.feature.user.service;

import com.bluetoya.taradiddle.common.exception.CustomException;
import com.bluetoya.taradiddle.common.exception.errorcode.UserErrorCode;
import com.mongodb.client.result.UpdateResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserBlockService {

    private final UserDomainService userDomainService;

    public List<String> getBlockedUsers(String userId) {
        return userDomainService.getBlockedUsers(userId);
    }

    public String addBlockUser(String userId, String blockUserId) {
        // 이미 차단된 유저인지 체크

        UpdateResult result = userDomainService.blockUser(userId, blockUserId);
        if (result.getModifiedCount() > 0) {
            return "유저 차단 성공";
        }

        throw new CustomException(UserErrorCode.BLOCK_USER_FAILED);
    }

    public String unblockUser(String userId, String blockUserId) {
        UpdateResult result = userDomainService.unblockUser(userId, blockUserId);
        if (result.getModifiedCount() > 0) {
            return "유저 차단 해제 성공";
        }

        throw new CustomException(UserErrorCode.BLOCK_USER_FAILED);
    }
}
