package dev.mongmeo.guestbook.web.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginDto {

  private String email;
  private String password;
}
