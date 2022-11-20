package dev.mongmeo.guestbook.persistence.map;

import dev.mongmeo.guestbook.domain.User;
import dev.mongmeo.guestbook.persistence.UserRepository;
import dev.mongmeo.guestbook.web.dto.request.UserCreateDto;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

@Repository
public class MapUserRepository implements UserRepository {

  private final Map<Long, User> store = new ConcurrentHashMap<>();
  private final AtomicLong sequence = new AtomicLong();

  @Override
  public boolean existByEmail(String email) {
    return store.values().stream().anyMatch(user -> user.getEmail().equals(email));
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return store.values().stream().filter(user -> user.getEmail().equals(email)).findFirst();
  }

  @Override
  public Optional<User> findById(long id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public User save(User user) {
    user.setId(sequence.incrementAndGet());
    user.setCreatedAt(LocalDateTime.now());
    store.put(user.getId(), user);
    return user;
  }

  @Override
  public void deleteById(long id) {
    store.remove(id);
  }

  @PostConstruct
  public void createTestData() {
    UserCreateDto user = new UserCreateDto();
    user.setEmail("test@test.com");
    user.setPassword("test1234");
    user.setNickname("mongmeo");
    save(user.toEntity());
  }
}
