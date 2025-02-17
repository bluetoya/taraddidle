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

    public User create(UserDto user) {
        return userRepository.save(User.of(user));
    }

    public User update(UserDto user) {
        User updatedUser = userRepository.findByUsername(user.getUsername());
        return userRepository.save(updatedUser.update(user));
    }

    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }
}
