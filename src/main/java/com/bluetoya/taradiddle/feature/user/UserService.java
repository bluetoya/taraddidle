package com.bluetoya.taradiddle.feature.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDomainService userDomainService;

    public User getUser(String userId) {
        return userDomainService.findById(userId);
    }

    public User update(String userId, UserDto updatedUser) {
//        User user = userRepository.findByUsername(updatedUser.getUsername());
//        return userRepository.save(user.update(updatedUser));
        return null;
    }

    public void delete(String userId) {
        userDomainService.deleteById(userId);
    }

}
