package com.bluetoya.taradiddle.feature.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final UserRepository userRepository;

    public User addFriend(String username, FriendDto friend) {
        User user = userRepository.findByUsername(username);
        return userRepository.save(user.addFriend(friend.name()));
    }

    public User removeFriend(String username, FriendDto friend) {
        User user = userRepository.findByUsername(username);
        return userRepository.save(user.removeFriend(friend.name()));
    }
}
