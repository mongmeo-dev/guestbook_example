package dev.mongmeo.guestbook.persistence.map;

import dev.mongmeo.guestbook.domain.Guestbook;
import dev.mongmeo.guestbook.persistence.GuestbookRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class MapGuestbookRepository implements GuestbookRepository {

  private final Map<Long, Guestbook> store = new ConcurrentHashMap<>();
  private final AtomicLong sequence = new AtomicLong();

  @Override
  public List<Guestbook> findAll() {
    return new ArrayList<>(store.values());
  }

  @Override
  public Optional<Guestbook> findById(long id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public Guestbook save(Guestbook guestbook) {
    guestbook.setId(sequence.incrementAndGet());
    guestbook.setCreatedAt(LocalDateTime.now());
    store.put(guestbook.getId(), guestbook);
    return guestbook;
  }

  @Override
  public void deleteById(long id) {
    store.remove(id);
  }
}
