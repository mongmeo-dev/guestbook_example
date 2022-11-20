package dev.mongmeo.guestbook.web.dto.response;

import dev.mongmeo.guestbook.domain.Guestbook;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GuestbookResponseDto {

  private long id;
  private String content;
  private long authorId;
  private String writeTime;

  public static GuestbookResponseDto fromEntity(Guestbook entity) {
    return GuestbookResponseDto.builder()
        .id(entity.getId())
        .content(entity.getContent())
        .authorId(entity.getAuthor().getId())
        .writeTime(entity.getCreatedAt().toString())
        .build();
  }
}
