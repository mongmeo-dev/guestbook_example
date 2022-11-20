package dev.mongmeo.guestbook.persistence;

import dev.mongmeo.guestbook.domain.Guestbook;
import java.util.List;
import java.util.Optional;

public interface GuestbookRepository {

  List<Guestbook> findAll();

  Optional<Guestbook> findById(long id);

  Guestbook save(Guestbook guestbook);

  void deleteById(long id);
}
