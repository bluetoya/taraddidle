package com.bluetoya.taradiddle.feature.user.entity;

import static com.bluetoya.taradiddle.feature.auth.entity.Auth.of;

import com.bluetoya.taradiddle.common.util.DateUtil;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.auth.entity.Auth;
import com.bluetoya.taradiddle.feature.user.enums.UserStatus;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private UserStatus userStatus;
    private Auth auth;
    private List<String> friends;
    private List<String> blockedUsers;
    private LocalDateTime updatedAt;

    public static User create(SignInRequest request, String encryptedPassword) {
        return User.builder()
            .email(request.email())
            .username(request.username())
            .firstName(request.firstName())
            .lastName(request.lastName())
            .userStatus(UserStatus.OFFLINE)
            .auth(of(encryptedPassword))
            .friends(Collections.emptyList())
            .blockedUsers(Collections.emptyList())
            .updatedAt(DateUtil.now())
            .build();
    }

    public User addFriend(String friend) {
        this.friends.add(friend);
        return this;
    }

    public User removeFriend(String name) {
        this.friends.remove(name);
        return this;
    }
}
