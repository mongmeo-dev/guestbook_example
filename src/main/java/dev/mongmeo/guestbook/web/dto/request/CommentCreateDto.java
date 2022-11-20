package dev.mongmeo.guestbook.web.dto.request;

import dev.mongmeo.guestbook.domain.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateDto {

  private String content;

  public Comment toEntity() {
    return Comment.builder()
        .content(this.content)
        .build();
  }

}
