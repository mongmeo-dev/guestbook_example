package dev.mongmeo.guestbook.web.controller.api;

import dev.mongmeo.guestbook.service.UserService;
import dev.mongmeo.guestbook.web.dto.request.UserCreateDto;
import dev.mongmeo.guestbook.web.dto.response.UserResponseDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserApiController {

  private final UserService userService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserResponseDto createUser(@RequestBody UserCreateDto dto) {
    return userService.createUser(dto);
  }

  @GetMapping("/{id}")
  public UserResponseDto getUserById(@PathVariable long id) {
    return userService.getUserById(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUserById(@PathVariable long id, HttpServletRequest request) {
    HttpSession session = request.getSession();
    Long logonUserId = (Long) session.getAttribute("logonUserId");
    userService.deleteUserById(id, logonUserId);
    session.removeAttribute("logonUserId");
  }
}

