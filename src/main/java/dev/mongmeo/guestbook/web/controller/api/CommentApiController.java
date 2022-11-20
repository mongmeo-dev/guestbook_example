package dev.mongmeo.guestbook.web.controller.api;

import dev.mongmeo.guestbook.service.CommentService;
import dev.mongmeo.guestbook.web.dto.request.CommentCreateDto;
import dev.mongmeo.guestbook.web.dto.response.CommentResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/guestbooks/{guestbookId}/comments")
public class CommentApiController {

  private final CommentService commentService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CommentResponseDto createComment(@PathVariable long guestbookId,
      @RequestBody CommentCreateDto dto,
      @SessionAttribute long logonUserId) {
    return commentService.save(dto, guestbookId, logonUserId);
  }

  @GetMapping
  public List<CommentResponseDto> getGuestbookComments(@PathVariable long guestbookId) {
    return commentService.getGuestbookComments(guestbookId);
  }

  @DeleteMapping("/{commentId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteComment(@PathVariable long commentId, @SessionAttribute long logonUserId) {
    commentService.deleteById(commentId, logonUserId);
  }
}
