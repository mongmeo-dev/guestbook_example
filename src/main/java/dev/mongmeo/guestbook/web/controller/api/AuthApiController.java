package dev.mongmeo.guestbook.web.controller.api;

import dev.mongmeo.guestbook.domain.User;
import dev.mongmeo.guestbook.service.AuthService;
import dev.mongmeo.guestbook.web.dto.request.LoginDto;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthApiController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<Void> login(@RequestBody LoginDto dto, HttpServletRequest request) {
    HttpSession session = request.getSession();
    Object logonUserId = session.getAttribute("logonUserId");

    if (Objects.nonNull(logonUserId)) {
      return ResponseEntity.badRequest().build();
    }

    User user = authService.matchEmailPassword(dto);

    session.setAttribute("logonUserId", user.getId());
    return ResponseEntity.ok().build();
  }

  @PostMapping("/logout")
  public ResponseEntity<Void> logout(HttpServletRequest request) {
    HttpSession session = request.getSession();
    Object logonUserId = session.getAttribute("logonUserId");

    if (Objects.isNull(logonUserId)) {
      return ResponseEntity.badRequest().build();
    }
    session.removeAttribute("logonUserId");

    return ResponseEntity.ok().build();
  }
}
