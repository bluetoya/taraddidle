package com.bluetoya.taradiddle.feature.user;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Builder
@Document(collection = "users")
public class User {

  @Id private String id;
  private String username;
  private String firstName;
  private String lastName;
  private LocalDateTime createdAt;

  public static User of(UserDto user) {
    return User.builder()
            .username(user.username())
            .firstName(user.firstName())
            .lastName(user.lastName())
            .createdAt(LocalDateTime.now())
            .build();
  }

  public User update(UserDto user) {
    this.firstName = user.firstName();
    this.lastName = user.lastName();
    this.createdAt = LocalDateTime.now();
    return this;
  }
}
