package com.bluetoya.taradiddle.feature.user;

import com.bluetoya.taradiddle.common.util.DateUtil;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.auth.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private List<String> friends;
    private LocalDateTime createdAt;
    private String authId;

    public static User of(SignInRequest request, String authId) {
        return User.builder()
            .username(request.username())
            .firstName(request.firstName())
            .lastName(request.lastName())
            .createdAt(LocalDateTime.now())
            .authId(authId)
            .build();
    }

    public User update(UserDto user) {
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.createdAt = DateUtil.now();
        return this;
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
