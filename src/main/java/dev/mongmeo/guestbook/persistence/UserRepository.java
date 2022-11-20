package dev.mongmeo.guestbook.persistence;

import dev.mongmeo.guestbook.domain.User;
import java.util.Optional;

public interface UserRepository {

  boolean existByEmail(String email);

  Optional<User> findByEmail(String email);

  Optional<User> findById(long id);

  User save(User user);

  void deleteById(long id);
}
