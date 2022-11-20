package dev.mongmeo.guestbook.service;

import dev.mongmeo.guestbook.domain.Comment;
import dev.mongmeo.guestbook.domain.Guestbook;
import dev.mongmeo.guestbook.domain.User;
import dev.mongmeo.guestbook.exception.UserRequestException;
import dev.mongmeo.guestbook.persistence.CommentRepository;
import dev.mongmeo.guestbook.persistence.GuestbookRepository;
import dev.mongmeo.guestbook.persistence.UserRepository;
import dev.mongmeo.guestbook.web.dto.request.CommentCreateDto;
import dev.mongmeo.guestbook.web.dto.response.CommentResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

  private final CommentRepository commentRepository;
  private final GuestbookRepository guestbookRepository;
  private final UserRepository userRepository;

  public List<CommentResponseDto> getGuestbookComments(long guestbookId) {
    return commentRepository.findCommentsByGuestbookId(guestbookId)
        .stream()
        .map(CommentResponseDto::fromEntity)
        .collect(Collectors.toList());
  }

  public CommentResponseDto save(CommentCreateDto dto, long guestbookId, long logonUserId) {
    Comment comment = dto.toEntity();

    setAuthorAndGuestbook(comment, guestbookId, logonUserId);

    Comment savedComment = commentRepository.save(comment);
    return CommentResponseDto.fromEntity(savedComment);
  }

  public void deleteById(long commentId, long logonUserId) {
    Comment comment = commentRepository.findCommentById(commentId)
        .orElseThrow(() -> new UserRequestException(HttpStatus.NOT_FOUND, "comment not found"));

    if (!comment.getAuthor().getId().equals(logonUserId)) {
      throw new UserRequestException(HttpStatus.FORBIDDEN, "forbidden");
    }

    commentRepository.deleteById(commentId);
  }

  private void setAuthorAndGuestbook(Comment comment, long guestbookId, long logonUserId) {
    User author = tryGetUserById(logonUserId);
    Guestbook guestbook = tryGetGuestbookById(guestbookId);
    comment.setAuthor(author);
    comment.setGuestbook(guestbook);
  }

  private Guestbook tryGetGuestbookById(long guestbookId) {
    return guestbookRepository.findById(guestbookId)
        .orElseThrow(() -> new UserRequestException(HttpStatus.NOT_FOUND, "guestbook not found"));
  }

  private User tryGetUserById(long logonUserId) {
    return userRepository.findById(logonUserId)
        .orElseThrow(() -> new UserRequestException(HttpStatus.UNAUTHORIZED, "unauthorized"));
  }
}
