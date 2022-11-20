package dev.mongmeo.guestbook.web.dto.request;

import dev.mongmeo.guestbook.domain.Guestbook;
import java.util.HashSet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestbookCreateDto {

  private String content;

  public Guestbook toEntity() {
    return Guestbook.builder()
        .content(this.content)
        .comments(new HashSet<>())
        .build();
  }
}
