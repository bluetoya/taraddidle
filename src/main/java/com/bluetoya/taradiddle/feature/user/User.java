package com.bluetoya.taradiddle.feature.user;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document(collection = "users")
public class User {

  @Id private String id;
  private String username;
  private String firstName;
  private String lastName;

  public static User of(UserDto user) {
    return User.builder()
            .username(user.getUsername())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .build();
  }

  public User update(UserDto user) {
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    return this;
  }
}
