package com.bluetoya.taradiddle.feature.user;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@Builder
@Document(collection = "users")
public class User {

  @Id private String id;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private List<String> friends;
  private LocalDateTime createdAt;

  public static User of(UserDto user) {
    return User.builder()
        .username(user.username())
        .password(user.password())
        .firstName(user.firstName())
        .lastName(user.lastName())
        .friends(Collections.emptyList())
        .createdAt(LocalDateTime.now())
        .build();
  }

  public User update(UserDto user) {
    this.firstName = user.firstName();
    this.lastName = user.lastName();
    this.createdAt = LocalDateTime.now();
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
