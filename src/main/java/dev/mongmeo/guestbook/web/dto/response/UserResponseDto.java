package dev.mongmeo.guestbook.web.dto.response;

import dev.mongmeo.guestbook.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDto {

  private long id;
  private String email;
  private String nickname;

  public static UserResponseDto fromEntity(User entity) {
    return UserResponseDto.builder()
        .id(entity.getId())
        .email(entity.getEmail())
        .nickname(entity.getNickname())
        .build();
  }
}
