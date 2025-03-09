package com.bluetoya.taradiddle.feature.user;

import static com.bluetoya.taradiddle.feature.auth.entity.Auth.of;

import com.bluetoya.taradiddle.common.util.DateUtil;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.auth.entity.Auth;
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
    private Auth auth;
    private List<String> friends;
    private LocalDateTime updatedAt;

    public static User create(SignInRequest request, String encryptedPassword) {
        return User.builder()
            .email(request.email())
            .username(request.username())
            .firstName(request.firstName())
            .lastName(request.lastName())
            .auth(of(encryptedPassword))
            .friends(Collections.emptyList())
            .updatedAt(DateUtil.now())
            .build();
    }

    public User update(UserDto user) {
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.updatedAt = DateUtil.now();
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
