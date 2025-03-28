package com.bluetoya.taradiddle.feature.user.service;

import com.bluetoya.taradiddle.feature.user.dto.FriendDto;
import com.bluetoya.taradiddle.feature.user.entity.User;
import com.bluetoya.taradiddle.feature.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final UserDomainService userDomainService;

    public User addFriend(String userId, FriendDto friend) {
        User user = userDomainService.findById(userId);

        return userDomainService.saveUser(user.addFriend(friend.name()));
    }

}
