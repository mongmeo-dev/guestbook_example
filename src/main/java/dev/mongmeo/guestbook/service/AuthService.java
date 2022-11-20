package dev.mongmeo.guestbook.service;

import dev.mongmeo.guestbook.domain.User;
import dev.mongmeo.guestbook.exception.UserRequestException;
import dev.mongmeo.guestbook.persistence.UserRepository;
import dev.mongmeo.guestbook.web.dto.request.LoginDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

  private final UserRepository userRepository;

  public User matchEmailPassword(LoginDto dto) {
    Optional<User> user = userRepository.findByEmail(dto.getEmail());
    if (user.isEmpty() || !user.get().getPassword().equals(dto.getPassword())) {
      throw new UserRequestException(HttpStatus.BAD_REQUEST, "Bad Credentials");
    }
    return user.get();
  }
}
