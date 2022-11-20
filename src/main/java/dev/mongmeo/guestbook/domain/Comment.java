package dev.mongmeo.guestbook.domain;

import dev.mongmeo.guestbook.exception.ServerException;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

  private Long id;
  private String content;
  private Guestbook guestbook;
  private User author;
  private LocalDateTime createdAt;

  public void setId(Long id) {
    if (Objects.nonNull(this.id)) {
      throw new ServerException(HttpStatus.INTERNAL_SERVER_ERROR, "id already exist");
    }
    this.id = id;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    if (Objects.nonNull(this.createdAt)) {
      throw new ServerException(HttpStatus.INTERNAL_SERVER_ERROR, "cannot update create time");
    }
    this.createdAt = createdAt;
  }

  public void setAuthor(User author) {
    if (Objects.nonNull(this.author)) {
      throw new ServerException(HttpStatus.INTERNAL_SERVER_ERROR, "cannot update author");
    }
    this.author = author;
  }

  public void setGuestbook(Guestbook guestbook) {
    if (Objects.nonNull(this.guestbook)) {
      throw new ServerException(HttpStatus.INTERNAL_SERVER_ERROR, "cannot update guestbook");
    }
    this.guestbook = guestbook;
    this.guestbook.getComments().add(this);
  }
}
