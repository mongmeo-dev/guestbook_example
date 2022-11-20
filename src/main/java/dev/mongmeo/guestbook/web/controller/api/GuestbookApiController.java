package dev.mongmeo.guestbook.web.controller.api;

import dev.mongmeo.guestbook.service.GuestbookService;
import dev.mongmeo.guestbook.web.dto.request.GuestbookCreateDto;
import dev.mongmeo.guestbook.web.dto.response.GuestbookResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/guestbooks")
public class GuestbookApiController {

  private final GuestbookService guestbookService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GuestbookResponseDto createGuestbook(@RequestBody GuestbookCreateDto dto,
      @SessionAttribute long logonUserId) {
    return guestbookService.save(dto, logonUserId);
  }

  @GetMapping
  public List<GuestbookResponseDto> getAllGuestbook() {
    return guestbookService.getAllGuestbook();
  }

  @GetMapping("/{id}")
  public GuestbookResponseDto getGuestbookById(@PathVariable long id) {
    return guestbookService.getGuestbookById(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteGuestbookById(@PathVariable long id, @SessionAttribute long logonUserId) {
    guestbookService.deleteGuestbookById(id, logonUserId);
  }
}
