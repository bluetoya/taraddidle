package com.bluetoya.taradiddle.feature.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public User update(UserDto updatedUser) {
        User user = userRepository.findByUsername(updatedUser.username());
        return userRepository.save(user.update(updatedUser));
    }

    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

}
