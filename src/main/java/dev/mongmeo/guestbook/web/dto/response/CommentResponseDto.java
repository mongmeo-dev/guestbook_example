package dev.mongmeo.guestbook.web.dto.response;

import dev.mongmeo.guestbook.domain.Comment;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponseDto {

  private long id;
  private String content;
  private long guestbookId;
  private long authorId;
  private String writeTime;

  public static CommentResponseDto fromEntity(Comment entity) {
    return CommentResponseDto.builder()
        .id(entity.getId())
        .content(entity.getContent())
        .guestbookId(entity.getGuestbook().getId())
        .authorId(entity.getAuthor().getId())
        .writeTime(entity.getCreatedAt().toString())
        .build();
  }
}
