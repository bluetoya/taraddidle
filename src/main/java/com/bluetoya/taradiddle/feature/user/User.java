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

  public static User ofMock() {
    return User.builder()
        .id("mockId")
        .username("mockUsername")
        .firstName("mockFirstName")
        .lastName("mockLastName")
        .build();
  }
}
