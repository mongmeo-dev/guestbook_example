package dev.mongmeo.guestbook.web.dto.request;

import dev.mongmeo.guestbook.domain.User;
import java.util.HashSet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDto {

  private String email;
  private String password;
  private String nickname;

  public User toEntity() {
    return User.builder()
        .email(this.email)
        .password(this.password)
        .nickname(this.nickname)
        .guestbooks(new HashSet<>())
        .build();
  }
}
