package com.bluetoya.taradiddle.feature.user;

import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDomainService userDomainService;

    public User getUser(String userId) {
        return userDomainService.findById(userId);
    }

    public String update(String userId, UserDto user) {
        UpdateResult result = userDomainService.updateUser(userId, user);
        if (result.getModifiedCount() > 0) {
            return "User updated successfully";
        } else {
            return "User update failed";
        }
    }

    // TODO :: update password

    public void delete(String userId) {
        userDomainService.deleteById(userId);
    }

}
