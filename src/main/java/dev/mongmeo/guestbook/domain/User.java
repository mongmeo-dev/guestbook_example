package dev.mongmeo.guestbook.domain;

import dev.mongmeo.guestbook.exception.ServerException;
import dev.mongmeo.guestbook.exception.UserRequestException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

  private Long id;
  private String email;
  private String password;
  private String nickname;
  private LocalDateTime createdAt;
  private Set<Guestbook> guestbooks;

  public void setId(Long id) {
    if (Objects.nonNull(this.id)) {
      throw new UserRequestException(HttpStatus.BAD_REQUEST, "id already exist");
    }
    this.id = id;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    if (Objects.nonNull(this.createdAt)) {
      throw new ServerException(HttpStatus.INTERNAL_SERVER_ERROR, "cannot update create time");
    }
    this.createdAt = createdAt;
  }
}
