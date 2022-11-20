package dev.mongmeo.guestbook.service;

import dev.mongmeo.guestbook.domain.User;
import dev.mongmeo.guestbook.exception.UserRequestException;
import dev.mongmeo.guestbook.persistence.UserRepository;
import dev.mongmeo.guestbook.web.dto.request.UserCreateDto;
import dev.mongmeo.guestbook.web.dto.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

  public UserResponseDto createUser(UserCreateDto dto) {
    if (userRepository.existByEmail(dto.getEmail())) {
      throw new UserRequestException(HttpStatus.BAD_REQUEST, "duplicated email");
    }
    User savedUser = userRepository.save(dto.toEntity());
    return UserResponseDto.fromEntity(savedUser);
  }

  public UserResponseDto getUserById(long id) {
    User foundUser = tryGetUserById(id);
    return UserResponseDto.fromEntity(foundUser);
  }

  public void deleteUserById(long id, long logonUserId) {
    User logonUser = tryGetUserById(id);
    if (!logonUser.getId().equals(logonUserId)) {
      throw new UserRequestException(HttpStatus.FORBIDDEN, "Forbidden");
    }
    userRepository.deleteById(id);
  }

  private User tryGetUserById(long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new UserRequestException(HttpStatus.NOT_FOUND, "user not found"));
  }
}
