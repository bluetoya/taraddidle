package com.bluetoya.taradiddle.feature.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDomainService userDomainService;

    public User getUser(String email) {
        return userDomainService.findByEmail(email);
    }

    public User update(UserDto updatedUser) {
//        User user = userRepository.findByUsername(updatedUser.getUsername());
//        return userRepository.save(user.update(updatedUser));
        return null;
    }

    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

}
