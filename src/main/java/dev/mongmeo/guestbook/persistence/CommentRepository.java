package dev.mongmeo.guestbook.persistence;

import dev.mongmeo.guestbook.domain.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentRepository {

  List<Comment> findCommentsByGuestbookId(long guestbookId);

  Optional<Comment> findCommentById(long id);

  Comment save(Comment comment);

  void deleteById(long id);
}
