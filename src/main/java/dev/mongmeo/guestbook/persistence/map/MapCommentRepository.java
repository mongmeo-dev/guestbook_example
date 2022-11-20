package dev.mongmeo.guestbook.persistence.map;

import dev.mongmeo.guestbook.domain.Comment;
import dev.mongmeo.guestbook.persistence.CommentRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class MapCommentRepository implements CommentRepository {

  private final Map<Long, Comment> store = new ConcurrentHashMap<>();
  private final AtomicLong sequence = new AtomicLong();

  @Override
  public List<Comment> findCommentsByGuestbookId(long guestbookId) {
    return store.values()
        .stream()
        .filter(comment -> comment.getGuestbook().getId().equals(guestbookId))
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Comment> findCommentById(long id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public Comment save(Comment comment) {
    comment.setId(sequence.incrementAndGet());
    comment.setCreatedAt(LocalDateTime.now());
    store.put(comment.getId(), comment);
    return comment;
  }

  @Override
  public void deleteById(long id) {
    store.remove(id);
  }
}
