package dev.mongmeo.guestbook.service;

import dev.mongmeo.guestbook.domain.Guestbook;
import dev.mongmeo.guestbook.domain.User;
import dev.mongmeo.guestbook.exception.UserRequestException;
import dev.mongmeo.guestbook.persistence.GuestbookRepository;
import dev.mongmeo.guestbook.persistence.UserRepository;
import dev.mongmeo.guestbook.web.dto.request.GuestbookCreateDto;
import dev.mongmeo.guestbook.web.dto.response.GuestbookResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GuestbookService {

  private final GuestbookRepository guestbookRepository;
  private final UserRepository userRepository;

  public List<GuestbookResponseDto> getAllGuestbook() {
    return guestbookRepository.findAll()
        .stream()
        .map(GuestbookResponseDto::fromEntity)
        .collect(Collectors.toList());
  }

  public GuestbookResponseDto getGuestbookById(long id) {
    Guestbook foundGuestbook = tryGetGuestbookById(id);
    return GuestbookResponseDto.fromEntity(foundGuestbook);
  }

  public GuestbookResponseDto save(GuestbookCreateDto dto, long logonUserId) {
    Guestbook guestbook = dto.toEntity();
    User author = userRepository.findById(logonUserId)
        .orElseThrow(() -> new UserRequestException(HttpStatus.UNAUTHORIZED, "unauthorized"));
    guestbook.setAuthor(author);
    Guestbook savedGuestbook = guestbookRepository.save(guestbook);
    return GuestbookResponseDto.fromEntity(savedGuestbook);
  }

  public void deleteGuestbookById(long id, long logonUserId) {
    Guestbook guestbook = tryGetGuestbookById(id);
    if (!guestbook.getAuthor().getId().equals(logonUserId)) {
      throw new UserRequestException(HttpStatus.FORBIDDEN, "forbidden");
    }
    guestbookRepository.deleteById(id);
  }

  private Guestbook tryGetGuestbookById(long id) {
    return guestbookRepository.findById(id)
        .orElseThrow(() -> new UserRequestException(HttpStatus.NOT_FOUND, "guestbook not found"));
  }
}
